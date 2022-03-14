package org.mentalizr.infra.processExecutor;

public class ProcessExecutionException extends Exception {

    public ProcessExecutionException() {
    }

    public ProcessExecutionException(String message) {
        super(message);
    }

    public ProcessExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessExecutionException(Throwable cause) {
        super(cause);
    }

    public ProcessExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
