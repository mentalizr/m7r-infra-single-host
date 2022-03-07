package org.mentalizr.infra.process.collect;

import java.util.Collections;
import java.util.List;

public class ProcessCollectResult {

    private final List<String> standardOut;
    private final List<String> errorOut;
    private final int exitCode;

    public ProcessCollectResult(List<String> standardOut, List<String> errorOut, int exitCode) {
        this.standardOut = Collections.unmodifiableList(standardOut);
        this.errorOut = Collections.unmodifiableList(errorOut);
        this.exitCode = exitCode;
    }

    public List<String> getStandardOut() {
        return standardOut;
    }

    public List<String> getErrorOut() {
        return errorOut;
    }

    public int getExitCode() {
        return exitCode;
    }
}