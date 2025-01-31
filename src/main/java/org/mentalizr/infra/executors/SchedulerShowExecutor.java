package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.console.Console;
import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.message.format.BlockFormat;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.cli.ConsoleWriter;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.scheduler.Scheduler;
import org.mentalizr.scheduler.configuration.JobConfigurations;
import org.mentalizr.scheduler.configuration.JobConfigurationsManager;
import org.mentalizr.scheduler.jobInitialization.JobOverview;
import org.mentalizr.scheduler.jobInitialization.JobOverview.JobOverviewRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class SchedulerShowExecutor implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerShowExecutor.class);

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);
        logger.info(SchedulerShowExecutor.class.getSimpleName() + " invoked.");

        Console.out(new MessageBuilder()
                .addText("scheduler:",
                        new BlockFormat.Builder()
                                .withWidth(18)
                                .build())
                .withNoLineFeed()
                .build());

        boolean schedulerRunning = Scheduler.isRunning();
        boolean schedulerActive = Scheduler.isActive();

        if (schedulerRunning) {
            Console.out(new MessageBuilder()
                    .addText("RUNNING", BlockFormat.GREEN_TEXT())
                    .withNoLineFeed()
                    .build());
        } else {
            Console.out(new MessageBuilder()
                    .addText("STOPPED", BlockFormat.RED_TEXT())
                    .withNoLineFeed()
                    .build());
        }
        if (schedulerActive) {
            Console.out(new MessageBuilder()
                    .addText(" ACTIVATED", BlockFormat.GREEN_TEXT())
                    .build());
        } else {
            Console.out(new MessageBuilder()
                    .addText(" DEACTIVATED", BlockFormat.RED_TEXT())
                    .build());
        }

        Console.out(new MessageBuilder()
                .addText("scheduler config:",
                        new BlockFormat.Builder()
                                .withWidth(18)
                                .build())
                .withNoLineFeed()
                .build());

//        System.out.println(deamonOutString);

//        String schedulerConfigConsistencyString = Strings.fillUpRight("scheduler config: ", ' ', 30);
        boolean consistent = Scheduler.hasConsistentConfiguration();
        if (consistent) {
            Console.out(new MessageBuilder()
                    .addText("UP-TO-DATE", BlockFormat.GREEN_TEXT())
                    .build());
        } else {
            Console.out(new MessageBuilder()
                    .addText("CHANGED", BlockFormat.GREEN_TEXT())
                    .build());
        }

        Console.println();

        JobConfigurations jobConfigurations = JobConfigurationsManager.fromConfigFiles();
        List<JobOverviewRecord> jobOverviewRecordList
                = JobOverview.getJobOverviewRecords(jobConfigurations);

        Console.out(new MessageBuilder()
                .addText("job name",
                        new BlockFormat.Builder()
                                .withWidth(30)
                                .withAbbreviationSign("*")
                                .build())
                .addText(" | ")
                .addText("job type",
                        new BlockFormat.Builder()
                                .withWidth(30)
                                .withAbbreviationSign("*")
                                .build())
                .addText(" | ")
                .addText("next execution time",
                        new BlockFormat.Builder()
                                .withWidth(30)
                                .withAbbreviationSign("*")
                                .build())
                .build());

        Console.println("-".repeat(30)
                        + "-+-"
                        + "-".repeat(30)
                        + "-+-"
                        + "-".repeat(30)
        );
//                        + "-+-"
//                        + "-".repeat(15));

        for (JobOverviewRecord jobOverviewRecord : jobOverviewRecordList) {
            Console.out(new MessageBuilder()
                    .addText(jobOverviewRecord.name(),
                            new BlockFormat.Builder()
                                    .withWidth(30)
                                    .withAbbreviationSign("*")
                                    .build())
                    .addText(" | ")
                    .addText(jobOverviewRecord.type(),
                            new BlockFormat.Builder()
                                    .withWidth(30)
                                    .withAbbreviationSign("*")
                                    .build())
                    .addText(" | ")
                    .addText(jobOverviewRecord.nextExecutionTime(),
                            new BlockFormat.Builder()
                                    .withWidth(30)
                                    .withAbbreviationSign("*")
                                    .build())
                    .build());
        }

    }

}
