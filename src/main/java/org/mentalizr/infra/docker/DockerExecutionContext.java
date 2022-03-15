package org.mentalizr.infra.docker;

import org.slf4j.Logger;

public class DockerExecutionContext {

    private final boolean verbose;
    private final Logger logger;

    public static class Builder {
        private boolean verbose = false;
        private Logger logger = null;

        public Builder beVerbose(boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        public Builder withLogger(Logger logger) {
            this.logger = logger;
            return this;
        }

        public DockerExecutionContext build() {
            return new DockerExecutionContext(this.verbose, this.logger);
        }
    }

    private DockerExecutionContext(boolean verbose, Logger logger) {
        this.verbose = verbose;
        this.logger = logger;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public Logger getLogger() {
        return logger;
    }

}
