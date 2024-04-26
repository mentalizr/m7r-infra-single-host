package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;

public class CleanImagesDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("clean-images")
                .withCommandExecutor(new CleanImagesExecutor())
                .withDescription("Remove all m7r related images including backups.")
                .build();
    }

}
