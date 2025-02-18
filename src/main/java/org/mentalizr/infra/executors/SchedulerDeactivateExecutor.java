package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.tasks.scheduler.DeactivateScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class SchedulerDeactivateExecutor implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerDeactivateExecutor.class);

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        logger.debug(SchedulerActivateExecutor.class.getSimpleName() + " invoked.");
        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run(DeactivateScheduler.NAME);
        if (result.isSuccess()) {
            logger.info("Scheduler successfully deactivated.");
        } else {
            logger.error("Deactivating scheduler failed. See scheduler log file for details.");
            throw new CommandExecutorException();
        }
    }

}
