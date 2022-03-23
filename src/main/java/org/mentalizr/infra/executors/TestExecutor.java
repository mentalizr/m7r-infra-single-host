package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.buildEntities.initFiles.nginx.LocalDevConf;
import org.mentalizr.infra.buildEntities.webAppResources.WebAppResources;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("test called.");

//        Path path = Paths.get("/home/m7radmin/gitrepos/m7r/core/m7r-frontend/node_modules/@fortawesome/fontawesome-free/webfonts");
//        System.out.println("exists? " + FileUtils.isExistingDirectory(path));

        WebAppResources.deploy();

//        System.out.println(LocalDevConf.getInstance().getContent());

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
