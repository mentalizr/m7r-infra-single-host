package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class SchedulerRestartDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("scheduler", "restart")
                .withCommandExecutor(new SchedulerRestartExecutor())
                .withDescription("Restart scheduler.")
                .build();
    }

}
