package org.mentalizr.infra.tasks;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.standard.StandardTaskRunner;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import org.mentalizr.infra.GlobalOptions;
import org.mentalizr.infra.appInit.ApplicationContext;

public class InfraTaskRunner {

    private static final int TASK_COLUMN_WIDTH = 33;

    public static TaskRunner create(CliCall cliCall) {
        boolean showStacktrace = cliCall.getOptionParserResultGlobal().hasOption(GlobalOptions.GLOBAL_OPTION__STACKTRACE);
        TaskRegistry taskRegistry = InfraTaskRegistry.create();
        return StandardTaskRunner.create(taskRegistry, showStacktrace, TASK_COLUMN_WIDTH);
    }

    public static TaskRunner create() {
        boolean stacktrace = ApplicationContext.showStacktrace();
        TaskRegistry taskRegistry = InfraTaskRegistry.create();
        return StandardTaskRunner.create(taskRegistry, stacktrace, TASK_COLUMN_WIDTH);
    }

}
