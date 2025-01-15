package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class DaemonDeactivateDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("daemon", "deactivate")
                .withCommandExecutor(new DaemonDeactivateExecutor())
                .withDescription("Deactivate daemon.")
                .build();
    }

}
