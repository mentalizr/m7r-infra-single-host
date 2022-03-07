package org.mentalizr.infra.process.handleOutput;

import java.io.*;
import java.util.List;

/**
 * Runs a process without any user input. Processing of output can be customized by {@link OutputHandler}.
 * Typical use case: e.g. calling a command like git.
 * For the sake of simplicity, error output stream is merged into standard output stream. Otherwise, a multithread
 * solution would be necessary. See
 * https://stackoverflow.com/questions/14165517/processbuilder-forwarding-stdout-and-stderr-of-started-processes-without-blocki
 */
public class ProcessWithOutputHandler {

    private final ProcessBuilder processBuilder;
    private final OutputHandler outputHandler;

    public ProcessWithOutputHandler(ProcessBuilder processBuilder, OutputHandler outputHandler) {
        this.processBuilder = processBuilder;
        this.outputHandler = outputHandler;
    }

    public int call() throws IOException, InterruptedException {
        this.processBuilder.redirectErrorStream(true);
        Process process = this.processBuilder.start();
        this.outputHandler.handleOutput(process.getInputStream());
        return process.waitFor();
    }

}
