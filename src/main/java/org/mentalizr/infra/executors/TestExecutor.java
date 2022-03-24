package org.mentalizr.infra.executors;

import de.arthurpicht.cli.CliCall;
import de.arthurpicht.cli.CommandExecutor;
import de.arthurpicht.cli.CommandExecutorException;
import org.mentalizr.infra.ExecutionContext;
import org.mentalizr.infra.buildEntities.html.HtmlChecksum;

public class TestExecutor implements CommandExecutor {

    @Override
    public void execute(CliCall cliCall) throws CommandExecutorException {
        ExecutionContext.initialize(cliCall);

        System.out.println("test called.");

//        Path path = Paths.get("/home/m7radmin/gitrepos/m7r/core/m7r-frontend/node_modules/@fortawesome/fontawesome-free/webfonts");
//        System.out.println("exists? " + FileUtils.isExistingDirectory(path));

        HtmlChecksum.writeToContainer();

//        System.out.println("Admin user initialized? " + M7rAdmin.isAdminUserInitialized());
//        M7rAdmin.init();

//        System.out.println("Has db created tables? " + DbSchema.hasDbCreatedTables());

//        try {
//            DbSchema.deploy();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        WebAppResources.deploy();

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
