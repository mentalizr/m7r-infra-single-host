package org.mentalizr.infra.executors;

import de.arthurpicht.cli.option.Option;
import de.arthurpicht.cli.option.OptionBuilder;

public class OptionsDef {

    public static final String SPECIFIC_OPTION__RECOVER_DEV = "recover_dev";
    public static final String SPECIFIC_OPTION__LATEST = "latest";
    public static final String SPECIFIC_OPTION__NO_RECOVER = "no-recover";


    public static Option getRecoverDefOption() {
        return new OptionBuilder()
                .withLongName("dev")
                .withShortName('d')
                .withDescription("recover from dev backup [~/.m7r-host/backup-default]")
                .build(SPECIFIC_OPTION__RECOVER_DEV);
    }

    public static Option getRecoverLatestOption() {
        return new OptionBuilder()
                .withLongName("latest")
                .withShortName('l')
                .withDescription("recover from latest backup in [~/.m7r-host/backup] (default)")
                .build(SPECIFIC_OPTION__LATEST);
    }

    public static Option getNoRecoverOption() {
        return new OptionBuilder()
                .withLongName("no-recover")
                .withShortName('x')
                .withDescription("omit recover")
                .build(SPECIFIC_OPTION__NO_RECOVER);
    }

}
