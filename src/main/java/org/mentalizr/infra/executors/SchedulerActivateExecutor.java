package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.tasks.scheduler.ActivateScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class SchedulerActivateExecutor implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerActivateExecutor.class);

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);
        logger.debug(SchedulerActivateExecutor.class.getSimpleName() + " invoked.");
        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run(ActivateScheduler.NAME);
        if (result.isSuccess()) {
            logger.info("Scheduler successfully activated.");
        } else {
            logger.error("Activating scheduler failed. See scheduler log file for details.");
            throw new CommandExecutorException();
        }
    }

}
