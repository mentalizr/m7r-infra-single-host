package org.mentalizr.infra.taskAgent;

import de.arthurpicht.cli.CliCall;
import org.mentalizr.infra.executors.OptionsDef;

public class RecoverSpecificOptions {

    public static boolean isRecoverDev(CliCall cliCall) {
        return cliCall.getOptionParserResultSpecific().hasOption(OptionsDef.SPECIFIC_OPTION__RECOVER_DEV);
    }

    public static boolean isRecoverFromLatest(CliCall cliCall) {
        return cliCall.getOptionParserResultSpecific().hasOption(OptionsDef.SPECIFIC_OPTION__LATEST);
    }

    public static boolean isOmitRecover(CliCall cliCall) {
        return cliCall.getOptionParserResultSpecific().hasOption(OptionsDef.SPECIFIC_OPTION__NO_RECOVER);
    }

}
