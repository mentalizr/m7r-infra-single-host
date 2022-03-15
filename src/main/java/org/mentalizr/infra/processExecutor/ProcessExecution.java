package org.mentalizr.infra.processExecutor;

import org.mentalizr.infra.processExecutor.outputHandler.generalOutputHandler.GeneralStandardErrorHandler;
import org.mentalizr.infra.processExecutor.outputHandler.generalOutputHandler.GeneralStandardOutHandler;
import org.slf4j.Logger;

import java.io.InputStream;
import java.util.List;

/**
 * Some end-user shortcuts for using process execution functionality.
 */
public class ProcessExecution {

    public static int executeInteractive(List<String> commandList) throws ProcessExecutionException {
        return executeInteractive(commandList.toArray(new String[0]));
    }

    public static int executeInteractive(String... commands) throws ProcessExecutionException {
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands(commands)
                .asInteractive()
                .build();
        return processExecutor.execute();
    }

    public static ProcessResultCollection execute(Logger logger, boolean verbose, String... commands) throws ProcessExecutionException {
        GeneralStandardOutHandler stdOutHandler = new GeneralStandardOutHandler(logger, verbose);
        GeneralStandardErrorHandler stdErrorHandler = new GeneralStandardErrorHandler(logger, verbose);
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands(commands)
                .withStandardOutHandler(stdOutHandler)
                .withStandardErrorHandler(stdErrorHandler)
                .build();

        processExecutor.execute();

        return new ProcessResultCollection(
                processExecutor, stdOutHandler, stdErrorHandler
        );
    }

    public static ProcessResultCollection execute(Logger logger, boolean verbose, InputStream inputStream, String... commands) throws ProcessExecutionException {
        GeneralStandardOutHandler stdOutHandler = new GeneralStandardOutHandler(logger, verbose);
        GeneralStandardErrorHandler stdErrorHandler = new GeneralStandardErrorHandler(logger, verbose);
        ProcessExecutor processExecutor = new ProcessExecutorBuilder()
                .withCommands(commands)
                .withInput(inputStream)
                .withStandardOutHandler(stdOutHandler)
                .withStandardErrorHandler(stdErrorHandler)
                .build();

        processExecutor.execute();

        return new ProcessResultCollection(
                processExecutor, stdOutHandler, stdErrorHandler
        );
    }

}
