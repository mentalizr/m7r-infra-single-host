package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class SchedulerDeactivateDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("scheduler", "deactivate")
                .withCommandExecutor(new SchedulerDeactivateExecutor())
                .withDescription("Deactivate scheduler.")
                .build();
    }

}
