package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;

public class StatusDef {

    public static final String SPECIFIC_OPTION__CONFIGURATION = "configuration";

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("status")
                .withSpecificOptions(new Options()
                        .add(new OptionBuilder()
                                .withLongName("configuration")
                                .withShortName('c')
                                .withDescription("show configuration also")
                                .build(SPECIFIC_OPTION__CONFIGURATION)))
                .withCommandExecutor(new StatusExecutor())
                .withDescription("Show status.")
                .build();
    }

}
