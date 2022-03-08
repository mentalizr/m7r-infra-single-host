package org.mentalizr.infra;

public class InfraRuntimeException extends RuntimeException {

    public InfraRuntimeException() {
    }

    public InfraRuntimeException(String message) {
        super(message);
    }

    public InfraRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfraRuntimeException(Throwable cause) {
        super(cause);
    }

    public InfraRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
