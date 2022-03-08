package org.mentalizr.infra;

public class IllegalInfraStateException extends InfraException {

    public IllegalInfraStateException() {
    }

    public IllegalInfraStateException(String message) {
        super(message);
    }

    public IllegalInfraStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInfraStateException(Throwable cause) {
        super(cause);
    }

    public IllegalInfraStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
