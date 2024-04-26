package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class RestartDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("restart")
                .withCommandExecutor(new RestartExecutor())
                .withDescription("Restarts docker infrastructure.")
                .build();
    }

}
