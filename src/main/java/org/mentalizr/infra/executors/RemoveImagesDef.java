package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;

public class RemoveImagesDef {

    public static final String SPECIFIC_OPTION__BACKUP = "backup";
    public static final String SPECIFIC_OPTION__NO_BACKUP = "no-backup";
    public static final String SPECIFIC_OPTION__ALL = "all";

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("remove-images")
                .withSpecificOptions(new Options()
                        .add(new OptionBuilder()
                                .withLongName(SPECIFIC_OPTION__BACKUP)
                                .withShortName('b')
                                .withDescription("create tagged backup before deleting (default)")
                                .build(SPECIFIC_OPTION__BACKUP))
                        .add(new OptionBuilder()
                                .withLongName(SPECIFIC_OPTION__NO_BACKUP)
                                .withShortName('n')
                                .withDescription("omit creating tagged backup before deleting")
                                .build(SPECIFIC_OPTION__NO_BACKUP))
                        .add(new OptionBuilder()
                                .withLongName(SPECIFIC_OPTION__ALL)
                                .withShortName('a')
                                .withDescription("delete all images including tagged backups")
                                .build(SPECIFIC_OPTION__ALL)))
                        .withCommandExecutor(new RemoveImagesExecutor())
                        .withDescription("Removes images.")
                        .build();
    }

}
