package org.mentalizr.infra.executors;

import de.arthurpicht.cli.command.CommandSequence;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;

public class FullPullDef {

    public static final String SPECIFIC_OPTION__PULL_IMAGES = "pull-images";
    public static final String SPECIFIC_OPTION__BUILD_IMAGES = "build-images";
    public static final String SPECIFIC_OPTION__NO_RECOVER = "no-recover";

    public static CommandSequence get() {

        return new CommandSequenceBuilder()
                .addCommands("fullpull")
                .withSpecificOptions(new Options()
                        .add(new OptionBuilder()
                                .withLongName("pull-images")
                                .withShortName('p')
                                .withDescription("pull all images from docker hub")
                                .build(SPECIFIC_OPTION__PULL_IMAGES))
                        .add(new OptionBuilder()
                                .withLongName("build-images")
                                .withShortName('b')
                                .withDescription("build images where possible (default)")
                                .build(SPECIFIC_OPTION__BUILD_IMAGES))
                        .add(OptionsDef.getRecoverDefOption())
                        .add(OptionsDef.getRecoverLatestOption())
                        .add(new OptionBuilder()
                                .withLongName("no-recover")
                                .withShortName('x')
                                .withDescription("omit recover")
                                .build(SPECIFIC_OPTION__NO_RECOVER)))
                .withCommandExecutor(new FullPullExecutor())
                .withDescription("Full pull-up of mentalizr docker infrastructure.")
                .build();
    }

}
