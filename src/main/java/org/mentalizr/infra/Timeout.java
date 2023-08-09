package org.mentalizr.infra;

public class Timeout {

    private final Integer timeoutSec;

    public static Timeout getDefaultTimeout() {
        return new Timeout(null);
    }

    public static Timeout getTimeout(int timeoutSec) {
        return new Timeout(timeoutSec);
    }

    private Timeout(Integer timeoutSec) {
        this.timeoutSec = timeoutSec;
    }

    public int getTimeoutSec(int defaultTimeout) {
        if (this.timeoutSec == null) return defaultTimeout;
        return this.timeoutSec;
    }

}
