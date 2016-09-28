/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psyriccio.ftpipe;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author psyriccio
 */
public class PipeOutStream extends ByteArrayOutputStream {

    private final FTPipe pipe;
    private boolean isCosing;

    public PipeOutStream(FTPipe pipe) {
        super();
        this.pipe = pipe;
        this.isCosing = false;
    }

    @Override
    public void close() throws IOException {
        if (!isCosing) {
            isCosing = true;
            flush();
            pipe.getInbound()
                    .add(new FTPipe.DataBlock(toByteArray()));
        }
        super.close();

    }

}
