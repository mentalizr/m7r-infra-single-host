package org.mentalizr.infra.executors;

public class ExecutionPreconditionFailedException extends Exception {
    public ExecutionPreconditionFailedException(String message) {
        super("Execution precondition failed. " + message);
    }
}
