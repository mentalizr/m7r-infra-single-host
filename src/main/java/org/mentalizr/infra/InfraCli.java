package org.mentalizr.infra;

import de.arthurpicht.cli.*;
import de.arthurpicht.cli.command.CommandSequenceBuilder;
import de.arthurpicht.cli.command.Commands;
import de.arthurpicht.cli.command.InfoDefaultCommand;
import de.arthurpicht.cli.common.UnrecognizedArgumentException;
import de.arthurpicht.cli.option.ManOption;
import de.arthurpicht.cli.option.OptionBuilder;
import de.arthurpicht.cli.option.Options;
import de.arthurpicht.cli.option.VersionOption;
import org.mentalizr.infra.executors.CreateExecutor;
import org.mentalizr.infra.executors.StatusExecutor;

public class InfraCli {

    public static final String OPTION_VERBOSE = "verbose";
    public static final String OPTION_STACKTRACE = "stacktrace";
    public static final String OPTION_SILENT = "silent";

    private static Cli createCli() {

        Options globalOptions = new Options()
                .add(new VersionOption())
                .add(new ManOption())
                .add(new OptionBuilder().withLongName("verbose").withDescription("verbose output").build(OPTION_VERBOSE))
                .add(new OptionBuilder().withShortName('s').withLongName("stacktrace").withDescription("Show stacktrace when running on error.").build(OPTION_STACKTRACE))
                .add(new OptionBuilder().withLongName("silent").withDescription("Make no output to console.").build(OPTION_SILENT));

        Commands commands = new Commands();

        commands.setDefaultCommand(new InfoDefaultCommand());

        commands.add(new CommandSequenceBuilder()
                .addCommands("status")
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

        CliDescription cliDescription = new CliDescriptionBuilder()
                .withDescription("mentalizr infra structure manager CLI\nhttps://github.com/mentalizr/m7r-infra-singel-host")
                .withVersionByTag("0.0.1-SNAPSHOT", "2022-03-03")
                .build("m7r-infra");

        return new CliBuilder()
                .withGlobalOptions(globalOptions)
                .withCommands(commands)
                .withAutoHelp()
                .build(cliDescription);
    }

    public static void main(String[] args) {
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
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (RuntimeException | AssertionError e) {
            System.out.println("RuntimeException: " + e.getMessage());
            if (showStacktrace) e.printStackTrace();
            System.exit(1);
        }

    }
}
