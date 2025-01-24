package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class SchedulerStartDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("scheduler", "start")
                .withCommandExecutor(new SchedulerStartExecutor())
                .withDescription("Start scheduler.")
                .build();
    }

}
