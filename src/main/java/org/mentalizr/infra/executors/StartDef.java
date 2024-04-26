package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class StartDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("start")
                .withCommandExecutor(new StartExecutor())
                .withDescription("Starts docker infrastructure.")
                .build();
    }

}
