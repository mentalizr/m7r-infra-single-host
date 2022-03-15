package org.mentalizr.infra.processExecutor.internals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessWrapperSingleProcess implements ProcessWrapper {

    private final ProcessBuilder processBuilder;
    private Process process;

    public ProcessWrapperSingleProcess(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

    @Override
    public void start() throws IOException, InterruptedException {
        this.process = this.processBuilder.start();
    }

    @Override
    public InputStream getStandardOut() {
        return this.process.getInputStream();
    }

    @Override
    public InputStream getErrorOut() {
        return this.process.getErrorStream();
    }

    @Override
    public OutputStream getStandardIn() {
        return this.process.getOutputStream();
    }

    @Override
    public int waitFor() throws InterruptedException {
        return this.process.waitFor();
    }

}
