package org.mentalizr.infra.docker.m7r;

import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.*;
import org.mentalizr.infra.buildEntities.ConfigFileInitMongoJs;
import org.mentalizr.infra.docker.Container;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.linux.LinuxExecutionException;
import org.mentalizr.infra.linux.User;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

public class M7rContainerMongo {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);

    public static boolean exists() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return Container.exists(context, Const.CONTAINER_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean isRunning() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return Container.isRunning(context, Const.CONTAINER_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean isStopped() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return !Container.isRunning(context, Const.CONTAINER_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

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

        int userId;
        try {
            userId = User.getUserId();
        } catch (LinuxExecutionException e) {
            throw new InfraRuntimeException(e);
        }

        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Docker.call(
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

            Path initMongoFile = configFileInitMongoJs.writeToM7rTempDir();
            DockerCopy.copyFileWithPreservedRights(context, initMongoFile, Const.CONTAINER_MONGO, "docker-entrypoint-initdb.d");
        } catch (IOException | DockerExecutionException e) {
            throw new InfraRuntimeException(e.getMessage(), e);
        }
    }

    public static void start() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Container.start(context, Const.CONTAINER_MONGO);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void stop() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Container.stop(context, Const.CONTAINER_MONGO);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Docker.call(
                    context,
                    "docker", "container", "rm", Const.CONTAINER_MONGO);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

}
