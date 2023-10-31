package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.Options;

public class PullUpDef {

    public static CommandSequence get() {

        return new CommandSequenceBuilder()
                .addCommands("pullup")
                .withSpecificOptions(new Options()
                        .add(OptionsDef.getRecoverDefOption())
                        .add(OptionsDef.getRecoverLatestOption())
                        .add(OptionsDef.getNoRecoverOption()))
                .withCommandExecutor(new PullUpExecutor())
                .withDescription("Pulls up mentalizr docker infrastructure.")
                .build();

    }

}
