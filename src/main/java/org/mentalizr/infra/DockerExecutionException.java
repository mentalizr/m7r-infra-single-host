package org.mentalizr.infra;

public class DockerExecutionException extends InfraException {

    public DockerExecutionException() {
    }

    public DockerExecutionException(String message) {
        super(message);
    }

    public DockerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DockerExecutionException(Throwable cause) {
        super(cause);
    }

    public DockerExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
