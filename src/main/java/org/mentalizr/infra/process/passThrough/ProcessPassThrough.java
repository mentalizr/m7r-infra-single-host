package org.mentalizr.infra.process.passThrough;

import java.io.IOException;

/**
 * Runs a process with user interaction, e.g. an editor. Input and output is passed through from standard IO
 * to executed process.
 *
 */
public class ProcessPassThrough {

    private final ProcessBuilder processBuilder;

    public ProcessPassThrough(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

    public int call() throws IOException, InterruptedException {
        this.processBuilder.inheritIO();
        return this.processBuilder.start().waitFor();
    }

}
