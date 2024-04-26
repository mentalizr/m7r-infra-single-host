package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class CreateImagesDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("create-images")
                .withCommandExecutor(new CreateImagesExecutor())
                .withDescription("Creates images.")
                .build();
    }

}
