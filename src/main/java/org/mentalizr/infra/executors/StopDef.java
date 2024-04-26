package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class StopDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("stop")
                .withCommandExecutor(new StopExecutor())
                .withDescription("Stops docker infrastructure.")
                .build();
    }

}
