package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class PullImagesDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("pull-images")
                .withCommandExecutor(new PullImagesExecutor())
                .withDescription("Pull images from Docker Hub.")
                .build();
    }

}
