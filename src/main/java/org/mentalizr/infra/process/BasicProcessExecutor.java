package org.mentalizr.infra.process;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class BasicProcessExecutor {

    private final ProcessBuilder processBuilder;

    protected BasicProcessExecutor(List<String> commands) {
        this.processBuilder = new ProcessBuilder().command(commands);
    }

    protected BasicProcessExecutor(String... commands) {
        this.processBuilder = new ProcessBuilder().command(commands);
    }

    protected void setWorkingDirectory(File directory) {
        this.processBuilder.directory(directory);
    }

    protected Map<String, String> getEnvironment() {
        return this.processBuilder.environment();
    }

    protected ProcessBuilder getProcessBuilder() {
        return this.processBuilder;
    }

}
