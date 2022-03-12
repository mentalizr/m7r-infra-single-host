package org.mentalizr.infra.process.collect;

import org.mentalizr.infra.process.BasicProcessExecutor;

import java.io.InputStream;
import java.util.List;

public final class ProcessCollectBuilder extends BasicProcessExecutor {

    private InputStream inputStream = null;

    public ProcessCollectBuilder(List<String> commands) {
        super(commands);
    }

    public ProcessCollectBuilder(String... commands) {
        super(commands);
    }

    public ProcessCollectBuilder withInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public ProcessCollect build() {
        return new ProcessCollect(getProcessBuilder(), this.inputStream);
    }

}
