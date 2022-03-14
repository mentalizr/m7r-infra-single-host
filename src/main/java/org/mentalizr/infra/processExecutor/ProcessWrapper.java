package org.mentalizr.infra.processExecutor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ProcessWrapper {

    /**
     * Starts the process.
     *
     * @throws IOException the IOException
     * @throws InterruptedException InterruptedException
     */
    void start() throws IOException, InterruptedException;

    /**
     * Returns the standard out of process as InputStream.
     *
     * @return standard out
     */
    InputStream getStandardOut();

    /**
     * Returns error out of process as InputStream.
     * @return error out
     */
    InputStream getErrorOut();

    /**
     * Returns standard in of process as OutputStream.
     *
     * @return standard in
     */
    OutputStream getStandardIn();

    /**
     * Waits for finishing process execution and returns exit code.
     *
     * @return exit code.
     */
    int waitFor() throws InterruptedException;

}
