package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class ShellSqlDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("shell", "sql")
                .withCommandExecutor(new ShellSqlExecutor())
                .withDescription("Opens mysql client on maria container as root.")
                .build();
    }

}
