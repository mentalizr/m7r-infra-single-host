package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class ShellTomcatDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("shell", "tomcat")
                .withCommandExecutor(new ShellTomcatExecutor())
                .withDescription("Opens shell on tomcat container.")
                .build();
    }

}
