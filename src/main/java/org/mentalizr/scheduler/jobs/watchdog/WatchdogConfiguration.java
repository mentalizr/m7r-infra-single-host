package org.mentalizr.scheduler.jobs.watchdog;

import org.mentalizr.scheduler.jobs.BaseConfiguration;
import org.mentalizr.scheduler.jobs.JobConfiguration;

public final class WatchdogConfiguration extends JobConfiguration {

    public static final String SECTION_NAME = "watchdog";

    public WatchdogConfiguration(
            BaseConfiguration baseConfiguration
    ) {
        super(baseConfiguration);
    }

}
