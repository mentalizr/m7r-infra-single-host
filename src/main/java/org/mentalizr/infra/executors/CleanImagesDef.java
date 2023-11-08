package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;

public class CleanImagesDef {

    public static final String SPECIFIC_OPTION__BACKUP = "backup";
    public static final String SPECIFIC_OPTION__NO_BACKUP = "no-backup";
    public static final String SPECIFIC_OPTION__ALL = "all";

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("clean-images")
                .withCommandExecutor(new CleanImagesExecutor())
                .withDescription("Clean images.")
                .build();
    }

}
