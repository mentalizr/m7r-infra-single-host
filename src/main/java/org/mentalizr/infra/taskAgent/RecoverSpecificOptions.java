package org.mentalizr.infra.taskAgent;

import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.InfraCli;
import org.mentalizr.infra.executors.OptionsDef;

public class RecoverSpecificOptions {

    public static boolean isRecoverDev() {
        return ExecutionContext.getCliCall().getOptionParserResultSpecific().hasOption(OptionsDef.SPECIFIC_OPTION__RECOVER_DEV);
    }

    public static boolean isRecoverFromLatest() {
        return ExecutionContext.getCliCall().getOptionParserResultSpecific().hasOption(OptionsDef.SPECIFIC_OPTION__LATEST);
    }

    public static boolean isOmitRecover() {
        return ExecutionContext.getCliCall().getOptionParserResultSpecific().hasOption(OptionsDef.SPECIFIC_OPTION__NO_RECOVER);
    }

}
