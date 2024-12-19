package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class DaemonStopDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("daemon", "stop")
                .withCommandExecutor(new DaemonStopExecutor())
                .withDescription("Stop daemon.")
                .build();
    }

}
