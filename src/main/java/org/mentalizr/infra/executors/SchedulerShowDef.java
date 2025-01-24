package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class SchedulerShowDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("scheduler", "show")
                .withCommandExecutor(new SchedulerShowExecutor())
                .withDescription("Show scheduler jobs.")
                .build();
    }

}
