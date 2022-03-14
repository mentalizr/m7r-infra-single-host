package org.mentalizr.infra.processExecutor;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class ProcessExecutorBuilder {

    private List<String> commands;
    private final List<List<String>> pipedCommands;
    private Path workingDirectory;
    private Map<String, String> environment;
    private InputStream inputStream;
    private StandardOutHandler standardOutHandler;
    private StandardErrorHandler standardErrorHandler;
    private boolean redirectStandardError;
    private boolean interactive;

    public ProcessExecutorBuilder() {
        super();
        this.pipedCommands = new ArrayList<>();
        this.inputStream = null;
        this.standardOutHandler = null;
        this.redirectStandardError = false;
        this.interactive = false;
    }

    public ProcessExecutorBuilder withCommands(String... commands) {
        assertArgumentNotNull("commands", commands);
        this.commands = Arrays.asList(commands);
        return this;
    }

    public ProcessExecutorBuilder withPipeToCommands(String... commands) {
        assertArgumentNotNull("commands", commands);
        List<String> pipedCommands = Arrays.asList(commands);
        this.pipedCommands.add(pipedCommands);
        return this;
    }

    private boolean hasPipedCommands() {
        return !this.pipedCommands.isEmpty();
    }

    public ProcessExecutorBuilder withWorkingDirectory(Path workingDirectory) {
        assertArgumentNotNull("commands", commands);
        this.workingDirectory = workingDirectory;
        return this;
    }

    public ProcessExecutorBuilder withEnvironmentVariables(Map<String, String> environment) {
        assertArgumentNotNull("environment", environment);
        this.environment = environment;
        return this;
    }

    public ProcessExecutorBuilder withInput(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public ProcessExecutorBuilder withStandardOutHandler(StandardOutHandler standardOutHandler) {
        this.standardOutHandler = standardOutHandler;
        return this;
    }

    public ProcessExecutorBuilder withStandardErrorHandler(StandardErrorHandler standardErrorHandler) {
        this.standardErrorHandler = standardErrorHandler;
        return this;
    }

    public ProcessExecutorBuilder withStandardErrorMergedIntoStandardOut() {
        this.redirectStandardError = true;
        return this;
    }

    /**
     * Mark commands as processed interactively. Input and all output handlers are ignored.
     * A combination with specified piped commands is not allowed and leads to a {@link IllegalStateException}.
     *
     * @return this {@link ProcessExecutorBuilder}
     */
    public ProcessExecutorBuilder asInteractive() {
        this.interactive = true;
        return this;
    }

    public ProcessExecutor build() {
        if (this.interactive) {
            if (!this.pipedCommands.isEmpty())
                throw new IllegalStateException("Cannot build ProcessExecutor. Combination of 'interactive' and 'pipedCommands' not allowed.");
            this.inputStream = null;
            this.standardOutHandler = null;
            this.standardErrorHandler = null;
            this.redirectStandardError = false;
        }

        ProcessBuilder processBuilder = createProcessBuilder(this.commands);

        if (!hasPipedCommands())
            return new ProcessExecutor(new ProcessWrapperSingleProcess(processBuilder), this.inputStream, this.standardOutHandler, this.standardErrorHandler);

        List<ProcessBuilder> processBuilderList = new ArrayList<>();
        processBuilderList.add(processBuilder);
        for (List<String> commands : this.pipedCommands) {
            processBuilder = createProcessBuilder(commands);
            processBuilderList.add(processBuilder);
        }
        return new ProcessExecutor(new ProcessWrapperPipedProcesses(processBuilderList), this.inputStream, this.standardOutHandler, this.standardErrorHandler);
    }

    private ProcessBuilder createProcessBuilder(List<String> commands) {
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        if (this.interactive) processBuilder.inheritIO();
        if (this.redirectStandardError) processBuilder.redirectErrorStream(true);
        if (this.workingDirectory != null) processBuilder.directory(this.workingDirectory.toFile());
        if (this.environment != null) {
            Map<String, String> environment = processBuilder.environment();
            environment.putAll(this.environment);
        }
        return processBuilder;
    }

}
