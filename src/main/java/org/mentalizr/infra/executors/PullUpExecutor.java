package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import de.arthurpicht.taskRunner.task.Task;
import de.arthurpicht.utils.core.collection.Lists;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.taskAgent.RecoverSpecificOptions;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.utils.ListUtils;
import org.mentalizr.infra.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PullUpExecutor implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(PullUpExecutor.class.getSimpleName());

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("pullup");

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        List<String> targetChain = Lists.newArrayList("create", "start", "deploy");
        if (RecoverSpecificOptions.isRecoverFromDefault()) {
            logger.info("execute pullup with recover for dev.");
            targetChain.add("recover-dev");
        } else if (RecoverSpecificOptions.isRecoverFromLatest()) {
            logger.info("execute pullup with recover from latest backup.");
            targetChain.add("recover-latest");
        } else if (RecoverSpecificOptions.isOmitRecover()){
            logger.info("execute pullup without recover.");
        } else {
            logger.info("execute pullup with recover from latest backup.");
            targetChain.add("recover-latest");
        }

        List<TaskRunnerResult> taskRunnerResults = taskRunner.run(StringUtils.toArray(targetChain));

        if (!ListUtils.getLastElement(taskRunnerResults).isSuccess())
            throw new CommandExecutorException();
    }

}
