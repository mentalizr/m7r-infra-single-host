package org.mentalizr.infra.processExecutor;

import java.util.ArrayList;
import java.util.List;

public class ProcessResultCollection {

    private final int exitCode;
    private final List<String> stdOutLines;
    private final List<String> errorOutLines;

    public ProcessResultCollection(
            ProcessExecutor processExecutor,
            CollectionHandler collectionHandlerStdOut) {
        this.exitCode = processExecutor.getExitCode();
        this.stdOutLines = collectionHandlerStdOut.getLines();
        this.errorOutLines = new ArrayList<>();
    }

    public ProcessResultCollection(
            ProcessExecutor processExecutor,
            CollectionHandler collectionHandlerStdOut,
            CollectionHandler collectionHandlerErrOut) {
        this.exitCode = processExecutor.getExitCode();
        this.stdOutLines = collectionHandlerStdOut.getLines();
        this.errorOutLines = collectionHandlerErrOut.getLines();
    }

    public int getExitCode() {
        return exitCode;
    }

    public List<String> getStandardOut() {
        return stdOutLines;
    }

    public List<String> getErrorOut() {
        return errorOutLines;
    }

}
