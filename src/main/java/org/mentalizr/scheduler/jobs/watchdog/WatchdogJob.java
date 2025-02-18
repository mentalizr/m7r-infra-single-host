package org.mentalizr.scheduler.jobs.watchdog;

import com.google.gson.Gson;
import org.mentalizr.infra.GlobalOptions;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.executors.Restart;
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

        logger.debug("Starting job [" + watchdogConfiguration.getJobName() + "] ...");

        ApplicationContext.initialize(new GlobalOptions(false, false, false, null, false));
        StatusSummary statusSummary = StatusSummary.create();

        if (statusSummary.isRunning()) {
            logger.debug("m7r infrastructure is running.");
        } else {
            logger.warn("m7r infrastructure not running. Try to restart ...");
            try {
                Restart.perform();
                logger.warn("m7r infrastructure restarted.");
            } catch (Restart.RestartException e) {
                logger.error("Restart failed.", e);
            }
        }

        logger.info("Job [" + watchdogConfiguration.getJobName() + "] executed successfully.");
    }

    @Override
    public WatchdogConfiguration getJobConfiguration(String jobConfigurationJson) {
        return new Gson().fromJson(jobConfigurationJson, WatchdogConfiguration.class);
    }

}
