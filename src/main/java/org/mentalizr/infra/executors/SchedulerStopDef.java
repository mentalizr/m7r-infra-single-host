package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class SchedulerStopDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("scheduler", "stop")
                .withCommandExecutor(new SchedulerStopExecutor())
                .withDescription("Stop scheduler.")
                .build();
    }

}
