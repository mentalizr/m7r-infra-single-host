package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class SchedulerActivateDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("scheduler", "activate")
                .withCommandExecutor(new SchedulerActivateExecutor())
                .withDescription("Activate scheduler.")
                .build();
    }

}
