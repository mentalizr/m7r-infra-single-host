package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class BackupDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("backup")
                .withCommandExecutor(new BackupExecutor())
                .withDescription("Backups databases.")
                .build();
    }

}
