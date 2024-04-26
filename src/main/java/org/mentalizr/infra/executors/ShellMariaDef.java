package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class ShellMariaDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("shell", "maria")
                .withCommandExecutor(new ShellMariaExecutor())
                .withDescription("Opens shell on maria container.")
                .build();
    }

}
