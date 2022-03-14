package org.mentalizr.infra.processExecutor;

public class ProcessExecutionExceptionCache {

    private ProcessExecutionException processExecutionExceptionStdOut;
    private ProcessExecutionException processExecutionExceptionStdError;

    public ProcessExecutionExceptionCache() {
        this.processExecutionExceptionStdOut = null;
        this.processExecutionExceptionStdError = null;
    }

    public void setProcessExecutionExceptionStdOut(Exception e) {
        this.processExecutionExceptionStdOut = new ProcessExecutionException(e);
    }

    public boolean hasProcessExecutionException() {
        return this.processExecutionExceptionStdOut != null || this.processExecutionExceptionStdError != null;
    }

    public ProcessExecutionException getProcessExecutionException() {
        if (!hasProcessExecutionException())
            throw new IllegalStateException(ProcessExecutionExceptionCache.class.getSimpleName() + " has no Exception.");
        if (this.processExecutionExceptionStdOut != null) return this.processExecutionExceptionStdOut;
        return this.processExecutionExceptionStdError;
    }

    public void setProcessExecutionExceptionStdError(Exception e) {
        this.processExecutionExceptionStdError = new ProcessExecutionException(e);
    }

}
