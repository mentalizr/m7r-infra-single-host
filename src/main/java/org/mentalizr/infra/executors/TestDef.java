package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class TestDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("test")
                .withCommandExecutor(new TestExecutor())
                .withDescription("Test.")
                .build();
    }

}
