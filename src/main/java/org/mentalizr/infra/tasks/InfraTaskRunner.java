package org.mentalizr.infra.tasks;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerBuilder;
import de.arthurpicht.taskRunner.taskRegistry.TaskRegistry;
import org.mentalizr.infra.InfraCli;

public class InfraTaskRunner {

    public static TaskRunner create(CliCall cliCall) {
        boolean showStacktrace = cliCall.getOptionParserResultGlobal().hasOption(InfraCli.OPTION_STACKTRACE);
        TaskRegistry taskRegistry = InfraTaskRegistry.create();
        return new TaskRunnerBuilder()
                .withTaskRegistry(taskRegistry)
                .withPreExecution(task -> {
                    System.out.print("[" + task.getName() + "] ... ");
                })
                .withSuccessExecution(task -> {
                    System.out.println("OK");
                })
                .withSkipExecution(task -> {
                    System.out.println("skipped");
                })
                .withFailByTaskPreconditionException((task, taskPreconditionException) -> {
                    System.out.println("Precondition failed: " + taskPreconditionException.getMessage());
                    if (showStacktrace) taskPreconditionException.printStackTrace();
                })
                .withFailByTaskExecutionException((task, taskExecutionException) -> {
                    System.out.println("Error: " + taskExecutionException.getMessage());
                    if (showStacktrace) taskExecutionException.printStackTrace();
                })
                .withFailByRuntimeException((task, runtimeException) -> {
                    System.out.println("RuntimeError: " + runtimeException.getMessage());
                    if (showStacktrace) runtimeException.printStackTrace();
                })
                .build();
    }
}
