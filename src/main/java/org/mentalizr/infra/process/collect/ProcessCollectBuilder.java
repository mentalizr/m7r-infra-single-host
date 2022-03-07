package org.mentalizr.infra.process.collect;

import org.mentalizr.infra.process.BasicProcessExecutor;

import java.util.List;

public final class ProcessCollectBuilder extends BasicProcessExecutor {

    public ProcessCollectBuilder(List<String> commands) {
        super(commands);
    }

    public ProcessCollectBuilder(String... commands) {
        super(commands);
    }

    public ProcessCollect build() {
        return new ProcessCollect(getProcessBuilder());
    }

}
