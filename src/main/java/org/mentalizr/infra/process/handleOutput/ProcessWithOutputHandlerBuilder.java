package org.mentalizr.infra.process.handleOutput;

import org.mentalizr.infra.process.BasicProcessExecutor;
import org.mentalizr.infra.process.collect.ProcessCollect;

import java.util.List;

public final class ProcessWithOutputHandlerBuilder extends BasicProcessExecutor {

    private OutputHandler outputHandler = null;

    public ProcessWithOutputHandlerBuilder(List<String> commands) {
        super(commands);
    }

    public ProcessWithOutputHandlerBuilder(String... commands) {
        super(commands);
    }

    public void withOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    public ProcessWithOutputHandler build() {
        if (this.outputHandler == null)
            throw new IllegalStateException(OutputHandler.class.getSimpleName() + " not specified.");
        return new ProcessWithOutputHandler(getProcessBuilder(), this.outputHandler);
    }

}
