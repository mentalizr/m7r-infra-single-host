package org.mentalizr.infra.docker.m7r;

import org.mentalizr.infra.*;
import org.mentalizr.infra.buildEntities.initFiles.ConfigFileInitMongoJs;
import org.mentalizr.infra.docker.Container;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.linux.LinuxExecutionException;
import org.mentalizr.infra.linux.User;
import org.mentalizr.infra.processExecutor.ProcessResultCollection;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

public class M7rContainerMongo {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);

    public static void create() {
        //docker create \
        //    --name $NAME_MONGO \
        //	--network $NETWORK \
        //	--network-alias mongo \
        //	--mount source=$VOL_MONGO,target=/data/db \
        //	-e MONGO_INITDB_ROOT_USERNAME=admin \
        //	-e MONGO_INITDB_ROOT_PASSWORD=geheim \
        //	-e MONGO_INITDB_DATABASE=admin \
        //	-p 27017:27017 \
        //	$MONGO_IMAGE >> /dev/null

        if (exists())
            throw new InfraRuntimeException("Cannot create container [" + Const.CONTAINER_MONGO + "]." +
                    " Already existing.");

        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        ProcessResultCollection result;
        try {
            result = Docker.call(
                    context,
                    "docker", "create",
                    "--name", Const.CONTAINER_MONGO,
                    "--network", Const.NETWORK,
                    "--network-alias", "mongo",
                    "--mount", "source=" + Const.VOLUME_MONGO +",target=/data/db",
//                    "-e", "MONGO_INITDB_ROOT_USERNAME=" + Configuration.getUserDbUser(),
//                    "-e", "MONGO_INITDB_ROOT_PASSWORD=" + Configuration.getDocumentDbPassword(),
                    "-e", "MONGO_INITDB_ROOT_USERNAME=admin",
                    "-e", "MONGO_INITDB_ROOT_PASSWORD=geheim",
                    "-e", "MONGO_INITDB_DATABASE=admin",
                    "-p", "27017:27017",
                    Const.IMAGE_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
        if (result.isFail()) throw M7rContainer.createInfraRuntimeException(result);
    }

    public static void initialize() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();

        // tar -cf - -C ${__init_rel} init-mongo.js --mode 777 --owner root --group root
        // | docker cp - $NAME_MONGO:/docker-entrypoint-initdb.d/

        try {
            ConfigFileInitMongoJs configFileInitMongoJs = ConfigFileInitMongoJs.getInstanceFromConfiguration();
            String messageHeader = "Copy configuration file [" + configFileInitMongoJs.getFileName() + "] to [" + Const.CONTAINER_MONGO + "]:";
            if (context.isVerbose()) {
                System.out.println(messageHeader);
                System.out.println(configFileInitMongoJs.getContent());
            }
            logger.debug(messageHeader);
            logger.debug(configFileInitMongoJs.getContent());

            Path initMongoFile = configFileInitMongoJs.writeToHostTempDir();
            DockerCopy.copyFileWithPreservedRights(context, initMongoFile, Const.CONTAINER_MONGO, "docker-entrypoint-initdb.d");
        } catch (IOException | DockerExecutionException e) {
            throw new InfraRuntimeException(e.getMessage(), e);
        }
    }

    public static boolean exists() {
        return M7rContainer.exists(Const.CONTAINER_MONGO);
    }

    public static void start() {
        M7rContainer.start(Const.CONTAINER_MONGO);
    }

    public static boolean isRunning() {
        return M7rContainer.isRunning(Const.CONTAINER_MONGO);
    }

    public static void stop() {
        M7rContainer.stop(Const.CONTAINER_MONGO);
    }

    public static boolean isStopped() {
        return M7rContainer.isStopped(Const.CONTAINER_MONGO);
    }

    public static void remove() {
        M7rContainer.remove(Const.CONTAINER_MONGO);
    }

}
