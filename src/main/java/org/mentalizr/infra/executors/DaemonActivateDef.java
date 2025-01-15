package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class DaemonActivateDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("daemon", "activate")
                .withCommandExecutor(new DaemonActivateExecutor())
                .withDescription("Activate daemon.")
                .build();
    }

}
