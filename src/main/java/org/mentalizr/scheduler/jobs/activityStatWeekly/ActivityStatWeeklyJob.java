package org.mentalizr.scheduler.jobs.activityStatWeekly;

import com.google.gson.Gson;
import org.mentalizer.mailer.MailConfiguration;
import org.mentalizer.mailer.MailConfigurationException;
import org.mentalizer.mailer.MailConfigurationLoader;
import org.mentalizr.cli.commands.user.activity.stat.activityStatPeriod.ActivityStatPeriod;
import org.mentalizr.cli.commands.user.activity.stat.activityStatPeriod.PeriodWeek;
import org.mentalizr.clientSdk.ClientSdkException;
import org.mentalizr.clientSdk.SessionAgent;
import org.mentalizr.clientSdk.activityStat.ActivityStat;
import org.mentalizr.clientSdk.activityStat.ActivityStatRequest;
import org.mentalizr.scheduler.jobs.SchedulerJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class ActivityStatWeeklyJob extends SchedulerJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ActivityStatWeeklyJob.class);

    @Override
    public void schedulerExecute(JobExecutionContext jobExecutionContext, String jobConfigurationJson)
            throws JobExecutionException {

        ActivityStatWeeklyConfiguration activityStatWeeklyConfiguration
                = getJobConfiguration(jobConfigurationJson);

        logger.info("Starting job [" + activityStatWeeklyConfiguration.getJobName() + "] ...");

        ActivityStatPeriod activityStatPeriod = new ActivityStatPeriod(new PeriodWeek(-1));
        ActivityStatRequest activityStatRequest
                = ActivityStatWeeklyHelper.createActivityStatRequest(
                activityStatPeriod,
                activityStatWeeklyConfiguration);

        try {
            SessionAgent sessionAgent = SessionAgent.createFromLocalConfigWithTransientCookieStorage();
            MailConfiguration mailConfiguration = obtainMailConfiguration();
            ActivityStat.exec(sessionAgent.getRESTCallContext(), activityStatRequest, mailConfiguration, false);
        } catch (ClientSdkException e) {
            throw new JobExecutionException(e);
        }

        logger.info("Job [" + activityStatWeeklyConfiguration.getJobName() + "] executed successfully.");
    }

    @Override
    public ActivityStatWeeklyConfiguration getJobConfiguration(String jobConfigurationJson) {
        return new Gson().fromJson(jobConfigurationJson, ActivityStatWeeklyConfiguration.class);
    }

    private MailConfiguration obtainMailConfiguration() throws JobExecutionException {
        try {
            return MailConfigurationLoader.load();
        } catch (MailConfigurationException e) {
            throw new RuntimeException("Error loading mail configuration: " + e.getMessage(), e);
        }
    }

}
