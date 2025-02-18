package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import de.arthurpicht.utils.core.collection.Lists;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.taskAgent.RecoverSpecificOptions;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PullUpExecutor implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(PullUpExecutor.class.getSimpleName());

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("pullup infrastructure");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        List<String> targetChain = Lists.newArrayList("create", "start", "deploy");
        if (RecoverSpecificOptions.isRecoverDev(cliCall)) {
            logger.info("execute pullup with recover for dev.");
            targetChain.add("recover-dev");
        } else if (RecoverSpecificOptions.isRecoverFromLatest(cliCall)) {
            logger.info("execute pullup with recover from latest backup.");
            targetChain.add("recover-latest");
        } else if (RecoverSpecificOptions.isOmitRecover(cliCall)) {
            logger.info("execute pullup without recover.");
        } else {
            logger.info("execute pullup with recover from latest backup.");
            targetChain.add("recover-latest");
        }

        List<TaskRunnerResult> taskRunnerResults = taskRunner.run(Strings.toArray(targetChain));

        if (!Lists.getLastElement(taskRunnerResults).isSuccess())
            throw new CommandExecutorException();
    }

}
