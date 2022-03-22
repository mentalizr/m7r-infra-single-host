package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.buildEntities.initFiles.nginx.LocalDevConf;

public class TestExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("test called.");

        System.out.println(LocalDevConf.getInstance().getContent());

//        try {
//            ConnectionMaria.awaitUp();
//            System.out.println("Success");
//        } catch (Exception e) {
//            System.out.println("Failed.");
//        }

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
