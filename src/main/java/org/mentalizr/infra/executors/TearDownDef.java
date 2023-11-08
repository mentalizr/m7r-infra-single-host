package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class TearDownDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("teardown")
                .withCommandExecutor(new TearDownExecutor())
                .withDescription("Tears down mentalizr docker infrastructure.")
                .build();
    }

}
