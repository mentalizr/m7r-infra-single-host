package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.utils.core.strings.Strings;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.infra.*;
import org.mentalizr.infra.processExecutor.ProcessExecution;
import org.mentalizr.infra.processExecutor.ProcessExecutionException;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LogsExecutor implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(LogsExecutor.class);

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("Show logs ...");

        Path logFile = LoggerUtils.getLogFile();
        if (!FileUtils.isExistingRegularFile(logFile))
            throw new CommandExecutorException("Log file not found: [" + logFile.toAbsolutePath() + "].");

        boolean follow = cliCall.getOptionParserResultSpecific().hasOption(InfraCli.SPECIFIC_OPTION_FOLLOW);

        showLogs(logFile, follow);
    }

    public void showLogs(Path file, boolean follow) throws CommandExecutorException {
        List<String> commands = new ArrayList<>();
        commands.add("tail");
        if (follow) {
            commands.add("-f");
        }
        commands.add(file.toAbsolutePath().toString());

        logger.info("execute >>> " + Strings.listing(commands, " "));
        try {
            ProcessExecution.executeInteractive(commands);
        } catch (ProcessExecutionException e) {
            throw new CommandExecutorException("Exception on showing logs. " + e.getMessage(), e);
        }
    }

}
