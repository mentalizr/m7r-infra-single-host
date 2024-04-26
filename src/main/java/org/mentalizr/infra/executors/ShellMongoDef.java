package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class ShellMongoDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("shell", "mongo")
                .withCommandExecutor(new ShellMongoExecutor())
                .withDescription("Opens shell on mongo container.")
                .build();
    }

}
