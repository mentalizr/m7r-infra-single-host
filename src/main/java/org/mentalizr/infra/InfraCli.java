package org.mentalizr.infra;

import de.arthurpicht.cli.*;
import de.arthurpicht.cli.command.Commands;
import de.arthurpicht.cli.command.InfoDefaultCommand;
import de.arthurpicht.cli.common.UnrecognizedArgumentException;
import de.arthurpicht.cli.option.*;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.appInit.ApplicationInitialization;
import org.mentalizr.infra.appInit.ApplicationInitializationException;
import org.mentalizr.infra.executors.*;

public class InfraCli {

    private static Cli createCli() {

        Commands commands = new Commands();
        commands.setDefaultCommand(new InfoDefaultCommand());
        commands.add(StatusDef.get());
        commands.add(CreateDef.get());
        commands.add(TestDef.get());
        commands.add(RemoveDef.get());
        commands.add(StartDef.get());
        commands.add(StopDef.get());
        commands.add(RestartDef.get());
        commands.add(PullUpDef.get());
        commands.add(FullPullDef.get());
        commands.add(TearDownDef.get());
        commands.add(DeployDef.get());
        commands.add(CleanDef.get());
        commands.add(RecoverDef.get());
        commands.add(BackupDef.get());
        commands.add(PullImagesDef.get());
        commands.add(CreateImagesDef.get());
        commands.add(RemoveImagesDef.get());
        commands.add(CleanImagesDef.get());
        commands.add(ShellMongoDef.get());
        commands.add(ShellMariaDef.get());
        commands.add(ShellTomcatDef.get());
        commands.add(ShellSqlDef.get());
        commands.add(LogsDef.get());
        commands.add(SchedulerStartDef.get());
        commands.add(SchedulerStopDef.get());
        commands.add(SchedulerActivateDef.get());
        commands.add(SchedulerDeactivateDef.get());
        commands.add(SchedulerRestartDef.get());
        commands.add(SchedulerShowDef.get());

        Options globalOptions = new Options()
                .add(new VersionOption())
                .add(new ManOption())
                .add(new OptionBuilder()
                        .withLongName("verbose")
                        .withDescription("verbose output")
                        .build(GlobalOptions.GLOBAL_OPTION__VERBOSE))
                .add(new OptionBuilder()
                        .withShortName('s')
                        .withLongName("stacktrace")
                        .withDescription("Show stacktrace when running on error.")
                        .build(GlobalOptions.GLOBAL_OPTION__STACKTRACE))
                .add(new OptionBuilder()
                        .withLongName("silent")
                        .withDescription("Make no output to console.")
                        .build(GlobalOptions.GLOBAL_OPTION__SILENT))
                .add(new OptionBuilder()
                        .withShortName('t')
                        .withLongName("timeout")
                        .withArgumentName("timeout")
                        .withDescription("Override default timeout parameters (seconds).")
                        .build(GlobalOptions.GLOBAL_OPTION__TIMEOUT))
                .add(new OptionBuilder()
                        .withShortName('n')
                        .withLongName("notify")
                        .withDescription("Send email notification to admins on command execution.")
                        .build(GlobalOptions.GLOBAL_OPTION__NOTIFY));

        CliDescription cliDescription = new CliDescriptionBuilder()
                .withDescription("mentalizr infra structure manager CLI\nhttps://github.com/mentalizr/m7r-infra")
                .withVersionByTag("0.0.1-SNAPSHOT", "2025-02-11")
                .build("m7r-infra");

        return new CliBuilder()
                .withGlobalOptions(globalOptions)
                .withCommands(commands)
                .withAutoHelp()
                .build(cliDescription);
    }

    public static void main(String[] args) {
        try {
            ApplicationInitialization.execute();
        } catch (ApplicationInitializationException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        Cli cli = createCli();
        CliCall cliCall = null;
        try {
            cliCall = cli.parse(args);
        } catch (UnrecognizedArgumentException e) {
            System.out.println(e.getExecutableName() + " call syntax error. " + e.getMessage());
            System.out.println(e.getCallString());
            System.out.println(e.getCallPointerString());
            System.exit(1);
        }

        boolean showStacktrace = cliCall.getOptionParserResultGlobal().hasOption(GlobalOptions.GLOBAL_OPTION__STACKTRACE);
        Timeout timeout = getTimeout(cliCall.getOptionParserResultGlobal());
        ApplicationContext.setTimeout(timeout);

        try {
            cli.execute(cliCall);
        } catch (CommandExecutorException e) {
            System.out.println("m7r-infra execution failed.");
            if (e.getMessage() != null) System.out.println(e.getMessage());
            System.exit(1);
        } catch (RuntimeException | AssertionError e) {
            System.out.println("RuntimeException: " + e.getMessage());
            if (showStacktrace) e.printStackTrace();
            System.exit(1);
        }
    }

    private static Timeout getTimeout(OptionParserResult optionParserResultGlobal) {
        if (optionParserResultGlobal.hasOption(GlobalOptions.GLOBAL_OPTION__TIMEOUT)) {
            String timeoutString = optionParserResultGlobal.getValue(GlobalOptions.GLOBAL_OPTION__TIMEOUT);
            try {
                int timeout = Integer.parseInt(timeoutString);
                return Timeout.getTimeout(timeout);
            } catch (NumberFormatException e) {
                throw new RuntimeException("No valid value for 'timeout' parameter.");
            }
        }
        return Timeout.getDefaultTimeout();
    }

}
