package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import org.mentalizr.commons.files.host.M7rInfraUserConfigFile;
import org.mentalizr.infra.ApplicationContext;
import org.mentalizr.infra.buildEntities.ConnectionMaria;

public class TestExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ApplicationContext.initialize(cliCall);

        System.out.println("test called.");

        System.setProperty("m7r.config", M7rInfraUserConfigFile.createInstance().toAbsolutePathString());

        try {
            ConnectionMaria.awaitUp();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Failed.");
        }

//        boolean success = ConnectionMaria.probe();
//        System.out.println("success? " + success);

//        try {
//            ConnectionMongo.probe();
//            System.out.println("Success");
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }


//        Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);
//        logger.info("test!");
//
//        Logger anotherLogger = LoggerFactory.getLogger("some.other");
//        anotherLogger.info("some other log statement.");

    }


}
