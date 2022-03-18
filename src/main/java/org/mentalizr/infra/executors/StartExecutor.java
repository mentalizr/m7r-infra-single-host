package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import org.mentalizr.commons.files.host.M7rInfraUserConfigFile;
import org.mentalizr.infra.ApplicationContext;
import org.mentalizr.infra.tasks.InfraTaskRunner;

public class StartExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ApplicationContext.initialize(cliCall);

        System.out.println("Start mentalizr infrastructure ...");

        System.setProperty(
                "m7r.config",
                M7rInfraUserConfigFile.createInstance().toAbsolutePathString());

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        TaskRunnerResult result = taskRunner.run("start");
    }

}
