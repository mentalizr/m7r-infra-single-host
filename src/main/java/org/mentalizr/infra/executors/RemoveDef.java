package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class RemoveDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("remove")
                .withCommandExecutor(new RemoveExecutor())
                .withDescription("Removes docker infrastructure.")
                .build();
    }

}
