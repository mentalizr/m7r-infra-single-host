package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class CreateDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("create")
                .withCommandExecutor(new CreateExecutor())
                .withDescription("Creates docker infrastructure.")
                .build();
    }

}
