package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.Options;

public class RecoverDef {

    public static CommandSequence get() {
        return new CommandSequenceBuilder()
                .addCommands("recover")
                .withSpecificOptions(new Options()
                        .add(OptionsDef.getRecoverDefOption())
                        .add(OptionsDef.getRecoverLatestOption()))
                .withCommandExecutor(new RecoverExecutor())
                .withDescription("Recovers databases.")
                .build();
    }

}
