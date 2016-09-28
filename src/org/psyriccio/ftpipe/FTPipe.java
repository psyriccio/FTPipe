/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psyriccio.ftpipe;

import ch.qos.logback.classic.Logger;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FileSystemFactory;
import org.apache.ftpserver.ftplet.FileSystemView;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpFile;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.slf4j.LoggerFactory;

/**
 *
 * @author psyriccio
 */
@Data
public class FTPipe implements FileSystemFactory {

    private static Logger log = (Logger) LoggerFactory.getLogger("ftpipe");

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataBlock {
        private byte[] data;
    }

    private final int port;
    private final String userName;
    private final String password;
    private final File baseDir;

    private FtpServerFactory ftpServerFactory;
    private ListenerFactory listenerFactory;
    private PropertiesUserManagerFactory propsUserManagerFactory;
    private UserManager userManager;
    private FtpServer ftpServer;

    private FTPipeVirtualFile root;
    private final ConcurrentLinkedQueue<DataBlock> inbound;
    private final ConcurrentLinkedQueue<DataBlock> outbound;
    private Date lastMod;
    private Date lastModIn;
    private Date lastModOut;

    private void initFTP() throws FtpException {
        ftpServerFactory = new FtpServerFactory();
        listenerFactory = new ListenerFactory();
        listenerFactory.setPort(port);
        ftpServerFactory.addListener("default", listenerFactory.createListener());
        propsUserManagerFactory = new PropertiesUserManagerFactory();
        //propsUserManagerFactory.setFile(new File(baseDir, "usr.properties"));
        propsUserManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        BaseUser user = new BaseUser();
        user.setName(this.userName);
        user.setPassword(this.password);
        user.setHomeDirectory("/" + userName);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);
        userManager = propsUserManagerFactory.createUserManager();
        userManager.save(user);
        ftpServerFactory.setUserManager(userManager);
        ftpServerFactory.setFileSystem(this);
        Map<String, Ftplet> ftplets = new HashMap<>();
        ftpServerFactory.setFtplets(ftplets);
        ftpServer = ftpServerFactory.createServer();
    }


    public FTPipe(File baseDir, int port, String userName, String password) throws FtpException {
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.baseDir = baseDir;
        this.inbound = new ConcurrentLinkedQueue<>();
        this.outbound = new ConcurrentLinkedQueue<>();
        Date date = new Date();
        this.lastMod = date;
        this.lastModIn = date;
        this.lastModOut = date;
        this.root = new FTPipeVirtualFile(this, FTPipeVirtualFile.Type.HomeRoot);
        initFTP();
    }

    public void start() throws FtpException, IOException {
        ftpServer.start();
        File marker = new File(baseDir, "ftp.pipe");
        String buf = "localhost:10200";
        Files.write(buf.getBytes("UTF-8"), marker);
    }

    public void stop() {
        ftpServer.stop();
    }

    @Override
    public FileSystemView createFileSystemView(User user) throws FtpException {
        final FTPipe thisPipe = this;
        return new FileSystemView() {
            @Override
            public FtpFile getHomeDirectory() throws FtpException {
                return root;
            }

            @Override
            public FtpFile getWorkingDirectory() throws FtpException {
                return root;
            }

            @Override
            public boolean changeWorkingDirectory(String string) throws FtpException {
                return string.equals("/") || string.equals(".") || string.equals("..");
            }

            @Override
            public FtpFile getFile(String string) throws FtpException {
                if(string.equals("/")) {
                    return root;
                }
                if(string.equals("/in") || string.equals("in")) {
                    return new FTPipeVirtualFile(thisPipe, FTPipeVirtualFile.Type.InStream);
                }
                if(string.equals("/out") || string.equals("out")) {
                    return new FTPipeVirtualFile(thisPipe, FTPipeVirtualFile.Type.OutStream);
                }
                return root;
            }

            @Override
            public boolean isRandomAccessible() throws FtpException {
                return false;
            }

            @Override
            public void dispose() {
                //
            }
        };
    }

}
