package org.mentalizr.infra.processExecutor;

import java.io.*;

public class ProcessExecutor {

    private final ProcessWrapper processWrapper;
    private final InputStream inputStream;
    private final StandardOutHandler standardOutHandler;
    private final StandardErrorHandler standardErrorHandler;
    private final ProcessExecutionExceptionCache processExecutionExceptionCache;

    private Thread handleStandardOutThreat;
    private Thread handleStandardErrorThreat;
    private boolean executed;

    public ProcessExecutor(ProcessWrapper processWrapper, InputStream inputStream, StandardOutHandler standardOutHandler, StandardErrorHandler standardErrorHandler) {
        this.processWrapper = processWrapper;
        this.inputStream = inputStream;
        this.standardOutHandler = standardOutHandler;
        this.standardErrorHandler = standardErrorHandler;
        this.processExecutionExceptionCache = new ProcessExecutionExceptionCache();
        this.handleStandardOutThreat = null;
        this.handleStandardErrorThreat = null;
        this.executed = false;
    }

    public int execute() throws ProcessExecutionException {

        if (this.executed) throw new IllegalStateException("Cannot execute " + ProcessExecutor.class.getSimpleName() + ". Was executed before.");
        this.executed = true;

        try {
            this.processWrapper.start();

            connectInput();
            handleStandardOut();
            handleStandardError();

            int exitCode = this.processWrapper.waitFor();

            if (handleStandardOutThreat != null) handleStandardOutThreat.join();
            if (handleStandardErrorThreat != null) handleStandardErrorThreat.join();

            if (this.processExecutionExceptionCache.hasProcessExecutionException())
                throw this.processExecutionExceptionCache.getProcessExecutionException();

            return exitCode;
        } catch (IOException | InterruptedException e) {
            throw new ProcessExecutionException(e);
        }
    }

    private void connectInput() throws IOException {
        if (this.inputStream != null) {
            OutputStream processStandardIn = this.processWrapper.getStandardIn();
            this.inputStream.transferTo(processStandardIn);
            processStandardIn.flush();
            processStandardIn.close();
        }
    }

    private void handleStandardOut() {
        this.handleStandardOutThreat = null;
        if (this.standardOutHandler != null) {
            this.handleStandardOutThreat = new Thread(() -> {
                try {
                    this.standardOutHandler.handleOutput(this.processWrapper.getStandardOut());
                } catch (IOException e) {
                    this.processExecutionExceptionCache.setProcessExecutionExceptionStdOut(e);
                }
            });
            this.handleStandardOutThreat.start();
        }
    }

    private void handleStandardError() {
        this.handleStandardErrorThreat = null;
        if (this.standardErrorHandler != null) {
            this.handleStandardErrorThreat = new Thread(() -> {
                try {
                    this.standardErrorHandler.handleOutput(this.processWrapper.getErrorOut());
                } catch (IOException e) {
                    this.processExecutionExceptionCache.setProcessExecutionExceptionStdError(e);
                }
            });
            this.handleStandardErrorThreat.start();
        }
    }

}
