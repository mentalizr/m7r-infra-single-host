package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import de.arthurpicht.utils.core.collection.Lists;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.tasks.removeImages.CreateBackups;
import org.mentalizr.infra.tasks.removeImages.RemoveImages;

import java.util.ArrayList;
import java.util.List;

public class RemoveImagesExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        checkParameterConsistency();
        System.out.println("Remove images");

        List<String> targetChain = new ArrayList<>();
        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        List<TaskRunnerResult> taskRunnerResults;
        if (!hasNoBackupParameter()) targetChain.add(CreateBackups.NAME);
        targetChain.add(RemoveImages.NAME);
        taskRunnerResults = taskRunner.run(Strings.toArray(targetChain));

        if (!Lists.getLastElement(taskRunnerResults).isSuccess())
            throw new CommandExecutorException();
    }

    private void checkParameterConsistency() throws CommandExecutorException {
        if (hasBackupParameter() && hasNoBackupParameter())
            throw new CommandExecutorException("Illegal combination of specific parameters: --"
                    + RemoveImagesDef.SPECIFIC_OPTION__NO_BACKUP + " and --" + RemoveImagesDef.SPECIFIC_OPTION__BACKUP + ".");
    }

    private boolean hasBackupParameter() {
        return ExecutionContext
                .getCliCall()
                .getOptionParserResultSpecific()
                .hasOption(RemoveImagesDef.SPECIFIC_OPTION__BACKUP);
    }

    private boolean hasNoBackupParameter() {
        return ExecutionContext
                .getCliCall()
                .getOptionParserResultSpecific()
                .hasOption(RemoveImagesDef.SPECIFIC_OPTION__NO_BACKUP);
    }

}
