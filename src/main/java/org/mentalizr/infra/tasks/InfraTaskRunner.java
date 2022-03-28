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
                .withOnPreExecuteCallback(task -> {
                    System.out.print("[" + task.getName() + "] ... ");
                })
                .withOnSuccessCallback(task -> {
                    System.out.println("OK");
                })
                .withOnSkipCallback(task -> {
                    System.out.println("SKIPPED");
                })
                .withOnUpToDateCallback(task -> {
                    System.out.println("UP-TO-DATE");
                })
                .withOnFailByTaskPreconditionExceptionCallback((task, taskPreconditionException) -> {
                    System.out.println("Precondition failed: " + taskPreconditionException.getMessage());
                    if (showStacktrace) taskPreconditionException.printStackTrace();
                })
                .withOnFailByTaskExecutionExceptionCallback((task, taskExecutionException) -> {
                    System.out.println("Error: " + taskExecutionException.getMessage());
                    if (showStacktrace) taskExecutionException.printStackTrace();
                })
                .withOnFailByRuntimeExceptionCallback((task, runtimeException) -> {
                    System.out.println("RuntimeError: " + runtimeException.getMessage());
                    if (showStacktrace) runtimeException.printStackTrace();
                })
                .build();
    }
}
