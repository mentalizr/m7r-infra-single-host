package org.mentalizr.infra.taskAgent;

import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.InfraCli;

public class RecoverSpecificOptions {

    public static boolean isRecoverFromDefault() {
        return ExecutionContext.getCliCall().getOptionParserResultSpecific().hasOption(InfraCli.SPECIFIC_OPTION_DEV);
    }

    public static boolean isRecoverFromLatest() {
        return ExecutionContext.getCliCall().getOptionParserResultSpecific().hasOption(InfraCli.SPECIFIC_OPTION_LATEST);
    }

    public static boolean isOmitRecover() {
        return ExecutionContext.getCliCall().getOptionParserResultSpecific().hasOption(InfraCli.SPECIFIC_OPTION_NO_RECOVER);
    }

}
