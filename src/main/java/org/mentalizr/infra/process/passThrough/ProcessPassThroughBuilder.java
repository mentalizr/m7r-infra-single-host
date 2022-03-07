package org.mentalizr.infra.process.passThrough;

import org.mentalizr.infra.process.BasicProcessExecutor;

import java.util.List;

public final class ProcessPassThroughBuilder extends BasicProcessExecutor {

    public ProcessPassThroughBuilder(List<String> commands) {
        super(commands);
    }

    public ProcessPassThroughBuilder(String... commands) {
        super(commands);
    }

    public ProcessPassThrough build() {
        return new ProcessPassThrough(getProcessBuilder());
    }

}
