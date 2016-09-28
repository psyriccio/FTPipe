/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psyriccio.ftpipe;

import com.google.common.collect.Lists;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.apache.ftpserver.ftplet.FtpFile;

/**
 *
 * @author psyriccio
 */
public class FTPipeVirtualFile implements FtpFile {

    public enum Type {
        HomeRoot, InStream, OutStream
    }

    private final FTPipe pipe;
    private final Type type;

    public FTPipeVirtualFile(FTPipe pipe, Type type) {
        this.pipe = pipe;
        this.type = type;
    }

    @Override
    public String getAbsolutePath() {
        switch (type) {
            case HomeRoot:
                return "/";
            case InStream:
                return "/in";
            case OutStream:
                return "/out";
        }
        return null;
    }

    @Override
    public String getName() {
        switch (type) {
            case HomeRoot:
                return "/";
            case InStream:
                return "in";
            case OutStream:
                return "out";
        }
        return null;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return type == Type.HomeRoot;
    }

    @Override
    public boolean isFile() {
        return type != Type.HomeRoot;
    }

    @Override
    public boolean doesExist() {
        switch (type) {
            case HomeRoot:
                return true;
            case InStream:
                return !pipe.getOutbound().isEmpty();
            case OutStream:
                return true;
        }
        return false;
    }

    @Override
    public boolean isReadable() {
        return (type == Type.InStream && !pipe.getOutbound().isEmpty())
                || type == Type.HomeRoot;
    }

    @Override
    public boolean isWritable() {
        return type == Type.OutStream;
    }

    @Override
    public boolean isRemovable() {
        return false;
    }

    @Override
    public String getOwnerName() {
        return pipe.getUserName();
    }

    @Override
    public String getGroupName() {
        return "ftpipe";
    }

    @Override
    public int getLinkCount() {
        return 1;
    }

    @Override
    public long getLastModified() {
        switch (type) {
            case HomeRoot:
                return pipe.getLastMod().getTime();
            case InStream:
                return pipe.getLastModOut().getTime();
            case OutStream:
                return pipe.getLastModIn().getTime();
        }
        return Date.from(Instant.EPOCH).getTime();
    }

    @Override
    public boolean setLastModified(long l) {
        return false;
    }

    @Override
    public long getSize() {
        FTPipe.DataBlock inData = pipe.getOutbound().peek();
        FTPipe.DataBlock outData = pipe.getInbound().peek();
        int inSize = inData == null ? 0 : inData.getData().length;
        int outSize = outData == null ? 0 : outData.getData().length;
        switch (type) {
            case HomeRoot:
                return inSize + outSize;
            case InStream:
                return inSize;
            case OutStream:
                return outSize;
        }
        return 0;
    }

    @Override
    public boolean mkdir() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean move(FtpFile ff) {
        return false;
    }

    @Override
    public List<FtpFile> listFiles() {
        if (type == Type.HomeRoot) {
            return Lists.newArrayList(new FTPipeVirtualFile(pipe, Type.InStream), new FTPipeVirtualFile(pipe, Type.OutStream));
        }
        return Lists.newArrayList();
    }

    @Override
    public OutputStream createOutputStream(long l) throws IOException {
        if (type == Type.OutStream) {
            return new PipeOutStream(pipe);
        }
        return null;
    }

    @Override
    public InputStream createInputStream(long l) throws IOException {
        if (type == Type.InStream) {
            FTPipe.DataBlock block = pipe.getOutbound().poll();
            if (block != null) {
                return new ByteArrayInputStream(block.getData());
            }
        }
        return null;
    }

}
