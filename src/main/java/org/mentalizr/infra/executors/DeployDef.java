package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;

public class DeployDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("deploy")
                .withCommandExecutor(new DeployExecutor())
                .withDescription("Deploys to server instances.")
                .build();
    }

}
