package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;

public class LogsDef {

    public static final String SPECIFIC_OPTION__FOLLOW = "follow";

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("logs")
                .withSpecificOptions(new Options()
                        .add(new OptionBuilder()
                                .withShortName('f')
                                .withLongName("follow")
                                .withDescription("Follow logs.")
                                .build(SPECIFIC_OPTION__FOLLOW)
                        ))
                .withCommandExecutor(new LogsExecutor())
                .withDescription("Show logs.")
                .build();
    }

}
