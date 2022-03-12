package org.mentalizr.infra.linux;

public class LinuxExecutionException extends Exception {

    public LinuxExecutionException() {
    }

    public LinuxExecutionException(String message) {
        super(message);
    }

    public LinuxExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinuxExecutionException(Throwable cause) {
        super(cause);
    }

    public LinuxExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
