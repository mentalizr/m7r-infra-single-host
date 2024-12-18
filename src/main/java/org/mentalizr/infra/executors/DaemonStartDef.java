package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;

public class DaemonStartDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("daemon", "start")
                .withCommandExecutor(new DaemonStartExecutor())
                .withDescription("Start daemon.")
                .build();
    }

}
