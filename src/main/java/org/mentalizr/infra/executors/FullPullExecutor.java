package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.cli.option.OptionParserResult;
import de.arthurpicht.taskRunner.TaskRunner;
import de.arthurpicht.taskRunner.runner.TaskRunnerResult;
import de.arthurpicht.utils.core.collection.Lists;
import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.taskAgent.RecoverSpecificOptions;
import org.mentalizr.infra.tasks.InfraTaskRunner;
import org.mentalizr.infra.tasks.createImages.CreateImages;
import org.mentalizr.infra.tasks.pullImages.PullImages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.mentalizr.infra.executors.FullPullDef.SPECIFIC_OPTION__PULL_IMAGES;

public class FullPullExecutor implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(FullPullExecutor.class.getSimpleName());

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        System.out.println("full pull-up infrastructure");
        checkParameterConsistency(cliCall);

        List<String> targetChain;
        if (isPullImages(cliCall)) {
            logger.info("execute full-pull with pulling images from docker hub.");
            targetChain = Lists.newArrayList(PullImages.NAME, "create", "start", "deploy");
        } else {
            logger.info("execute full-pull with creating images locally where possible.");
            targetChain = Lists.newArrayList(CreateImages.NAME, "create", "start", "deploy");
        }

        if (RecoverSpecificOptions.isRecoverDev(cliCall)) {
            logger.info("execute full-pull with recover for dev.");
            targetChain.add("recover-dev");
        } else if (RecoverSpecificOptions.isRecoverFromLatest(cliCall)) {
            logger.info("execute full-pull with recover from latest backup.");
            targetChain.add("recover-latest");
        } else if (RecoverSpecificOptions.isOmitRecover(cliCall)) {
            logger.info("execute full-pull without recover.");
        } else {
            logger.info("execute full-pull with recover from latest backup.");
            targetChain.add("recover-latest");
        }

        TaskRunner taskRunner = InfraTaskRunner.create(cliCall);
        List<TaskRunnerResult> taskRunnerResults = taskRunner.run(Strings.toArray(targetChain));

        if (!Lists.getLastElement(taskRunnerResults).isSuccess())
            throw new CommandExecutorException();
    }

    private void checkParameterConsistency(CliCall cliCall) throws CommandExecutorException {
        OptionParserResult optionParserResultSpecific = cliCall.getOptionParserResultSpecific();
        if (optionParserResultSpecific.hasOption(SPECIFIC_OPTION__PULL_IMAGES)
                && optionParserResultSpecific.hasOption(FullPullDef.SPECIFIC_OPTION__BUILD_IMAGES)) {
            throw new CommandExecutorException("Illegal combination of specific parameters: --"
                    + SPECIFIC_OPTION__PULL_IMAGES
                    + " and --" + FullPullDef.SPECIFIC_OPTION__BUILD_IMAGES + ".");
        }
    }

    private boolean isPullImages(CliCall cliCall) {
        return cliCall.getOptionParserResultSpecific().hasOption(SPECIFIC_OPTION__PULL_IMAGES);
    }

}
