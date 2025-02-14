package org.mentalizr.infra.tasks;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.standard.StandardTaskRunner;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import org.mentalizr.infra.GlobalOptions;

public class InfraTaskRunner {

    public static TaskRunner create(CliCall cliCall) {
        boolean showStacktrace = cliCall.getOptionParserResultGlobal().hasOption(GlobalOptions.GLOBAL_OPTION__STACKTRACE);
        TaskRegistry taskRegistry = InfraTaskRegistry.create();
        return StandardTaskRunner.create(taskRegistry, showStacktrace, 33);
    }

}
