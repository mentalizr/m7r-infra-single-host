package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;

public class CleanDef {

    public static final String SPECIFIC_OPTION__ALL_IMAGES = "all-images";

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("clean")
                .withSpecificOptions(new Options()
                        .add(new OptionBuilder()
                                .withLongName(SPECIFIC_OPTION__ALL_IMAGES)
                                .withShortName('a')
                                .withDescription("delete all m7r related images")
                                .build(SPECIFIC_OPTION__ALL_IMAGES)))
                        .withCommandExecutor(new CleanExecutor())
                .withDescription("Cleans mentalizr docker infrastructure.")
                .build();
    }

}
