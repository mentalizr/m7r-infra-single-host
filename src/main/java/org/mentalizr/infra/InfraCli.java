package org.mentalizr.infra;

import de.arthurpicht.cli.*;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.command.Commands;
import de.arthurpicht.cli.command.InfoDefaultCommand;
import de.arthurpicht.cli.common.UnrecognizedArgumentException;
import de.arthurpicht.cli.option.*;
import org.mentalizr.infra.appInit.ApplicationInitialization;
import org.mentalizr.infra.appInit.ApplicationInitializationException;
import org.mentalizr.infra.executors.*;

public class InfraCli {

    public static final String OPTION_VERBOSE = "verbose";
    public static final String OPTION_STACKTRACE = "stacktrace";
    public static final String OPTION_SILENT = "silent";

    public static final String SPECIFIC_OPTION_FOLLOW = "follow";
    public static final String SPECIFIC_OPTION_CONFIGURATION = "configuration";

    public static final String SPECIFIC_OPTION_DEV = "default";
    public static final String SPECIFIC_OPTION_LATEST = "latest";
    public static final String SPECIFIC_OPTION_NO_RECOVER = "no-recover";

    private static Cli createCli() {

        Options globalOptions = new Options()
                .add(new VersionOption())
                .add(new ManOption())
                .add(new OptionBuilder().withLongName("verbose").withDescription("verbose output").build(OPTION_VERBOSE))
                .add(new OptionBuilder().withShortName('s').withLongName("stacktrace").withDescription("Show stacktrace when running on error.").build(OPTION_STACKTRACE))
                .add(new OptionBuilder().withLongName("silent").withDescription("Make no output to console.").build(OPTION_SILENT));

        Option recoverDevOption = new OptionBuilder()
                .withLongName("dev")
                .withShortName('d')
                .withDescription("recover from dev backup [~/.m7r-host/backup-default]")
                .build(SPECIFIC_OPTION_DEV);

        Option recoverLatestOption = new OptionBuilder()
                .withLongName("latest")
                .withShortName('l')
                .withDescription("recover from latest backup in [~/.m7r-host/backup] (default)")
                .build(SPECIFIC_OPTION_LATEST);

        Commands commands = new Commands();

        commands.setDefaultCommand(new InfoDefaultCommand());

        Options specificOptionsStatus = new Options()
                .add(new OptionBuilder().withLongName("configuration").withShortName('c').withDescription("show configuration also").build(SPECIFIC_OPTION_CONFIGURATION));

        commands.add(new CommandSequenceBuilder()
                .addCommands("status")
                .withSpecificOptions(specificOptionsStatus)
                .withCommandExecutor(new StatusExecutor())
                .withDescription("Show status.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("create")
                .withCommandExecutor(new CreateExecutor())
                .withDescription("Creates docker infrastructure.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("test")
                .withCommandExecutor(new TestExecutor())
                .withDescription("Test.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("remove")
                .withCommandExecutor(new RemoveExecutor())
                .withDescription("Removes docker infrastructure.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("start")
                .withCommandExecutor(new StartExecutor())
                .withDescription("Starts docker infrastructure.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("stop")
                .withCommandExecutor(new StopExecutor())
                .withDescription("Stops docker infrastructure.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("restart")
                .withCommandExecutor(new RestartExecutor())
                .withDescription("Restarts docker infrastructure.")
                .build()
        );

        Options specificOptionsPullUp = new Options()
                .add(recoverDevOption)
                .add(recoverLatestOption)
                .add(new OptionBuilder()
                        .withLongName("no-recover")
                        .withShortName('x')
                        .withDescription("omit recover")
                        .build(SPECIFIC_OPTION_NO_RECOVER));

        commands.add(new CommandSequenceBuilder()
                .addCommands("pullup")
                .withSpecificOptions(specificOptionsPullUp)
                .withCommandExecutor(new PullUpExecutor())
                .withDescription("Pulls up mentalizr docker infrastructure.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("fullpull")
                .withSpecificOptions(specificOptionsPullUp)
                .withCommandExecutor(new FullPullExecutor())
                .withDescription("Full pull-up of mentalizr docker infrastructure.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("teardown")
                .withCommandExecutor(new TearDownExecutor())
                .withDescription("Tears down mentalizr docker infrastructure.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("deploy")
                .withCommandExecutor(new DeployExecutor())
                .withDescription("Deploys to server instances.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("clean")
                .withCommandExecutor(new TearDownExecutor())
                .withDescription("Cleans mentalizr docker infrastructure.")
                .build()
        );

        Options specificOptionsRecover = new Options()
                .add(recoverDevOption)
                .add(recoverLatestOption);

//        Options specificOptionsRecover = new Options()
//                .add(new OptionBuilder()
//                        .withLongName("dev")
//                        .withShortName('d')
//                        .withDescription("recover from dev backup [~/.m7r-host/backup-default]")
//                        .build(SPECIFIC_OPTION_DEV))
//                .add(new OptionBuilder()
//                        .withLongName("latest")
//                        .withShortName('l')
//                        .withDescription("recover from latest backup in [~/.m7r-host/backup] (default)")
//                        .build(SPECIFIC_OPTION_LATEST));

        commands.add(new CommandSequenceBuilder()
                .addCommands("recover")
                .withSpecificOptions(specificOptionsRecover)
                .withCommandExecutor(new RecoverExecutor())
                .withDescription("Recovers databases.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("backup")
                .withCommandExecutor(new BackupExecutor())
                .withDescription("Backups databases.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("create-images")
                .withCommandExecutor(new CreateImagesExecutor())
                .withDescription("Creates images.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("remove-images")
                .withCommandExecutor(new RemoveImagesExecutor())
                .withDescription("Removes images.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("shell", "mongo")
                .withCommandExecutor(new ShellMongoExecutor())
                .withDescription("Opens shell on mongo container.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("shell", "maria")
                .withCommandExecutor(new ShellMariaExecutor())
                .withDescription("Opens shell on maria container.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("shell", "tomcat")
                .withCommandExecutor(new ShellTomcatExecutor())
                .withDescription("Opens shell on tomcat container.")
                .build()
        );

        commands.add(new CommandSequenceBuilder()
                .addCommands("shell", "sql")
                .withCommandExecutor(new ShellSqlExecutor())
                .withDescription("Opens mysql client on maria container as root.")
                .build()
        );

        Options specificLogsOptions = new Options()
                .add(new OptionBuilder().withShortName('f').withLongName("follow").withDescription("Follow logs.").build(SPECIFIC_OPTION_FOLLOW));

        commands.add(new CommandSequenceBuilder()
                .addCommands("logs")
                .withSpecificOptions(specificLogsOptions)
                .withCommandExecutor(new LogsExecutor())
                .withDescription("Show logs.")
                .build()
        );

        CliDescription cliDescription = new CliDescriptionBuilder()
                .withDescription("mentalizr infra structure manager CLI\nhttps://github.com/mentalizr/m7r-infra")
                .withVersionByTag("0.0.1-SNAPSHOT", "2023-01-26")
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

        boolean showStacktrace = cliCall.getOptionParserResultGlobal().hasOption(OPTION_STACKTRACE);

        try {
            cli.execute(cliCall);
        } catch (CommandExecutorException e) {
            System.out.println("m7r-instance execution failed.");
            if (e.getMessage() != null) System.out.println(e.getMessage());
            System.exit(1);
        } catch (RuntimeException | AssertionError e) {
            System.out.println("RuntimeException: " + e.getMessage());
            if (showStacktrace) e.printStackTrace();
            System.exit(1);
        }
    }
}
