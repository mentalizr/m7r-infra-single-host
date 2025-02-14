package org.mentalizr.scheduler.jobs.watchdog;

import com.google.gson.Gson;
import org.mentalizr.infra.externalApi.StatusSummary;
import org.mentalizr.scheduler.jobs.SchedulerJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class WatchdogJob extends SchedulerJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(WatchdogJob.class);

    @Override
    public void schedulerExecute(JobExecutionContext jobExecutionContext, String jobConfigurationJson)
            throws JobExecutionException {

        WatchdogConfiguration watchdogConfiguration
                = getJobConfiguration(jobConfigurationJson);

        logger.info("Starting job [" + watchdogConfiguration.getJobName() + "] ...");

        StatusSummary statusSummary = StatusSummary.create();
        // ...

        logger.info("Job [" + watchdogConfiguration.getJobName() + "] executed successfully.");
    }

    @Override
    public WatchdogConfiguration getJobConfiguration(String jobConfigurationJson) {
        return new Gson().fromJson(jobConfigurationJson, WatchdogConfiguration.class);
    }

}
