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

public class M7rContainerMaria {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);

    public static boolean exists() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return Container.exists(context, Const.CONTAINER_MARIA);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean isRunning() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return Container.isRunning(context, Const.CONTAINER_MARIA);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static boolean isStopped() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            return !Container.isRunning(context, Const.CONTAINER_MARIA);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void create() {
        if (exists())
            throw new InfraRuntimeException("Cannot create container [" + Const.CONTAINER_MARIA + "]." +
                    " Already existing.");

        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Docker.call(
                    context,
                    "docker", "create",
                    "--name", Const.CONTAINER_MARIA,
                    "--network", Const.NETWORK,
                    "--network-alias", "maria",
                    "--mount", "source=" + Const.VOLUME_MARIA +",target=/var/lib/mysql",
                    "-e", "MYSQL_ROOT_PASSWORD=" + Configuration.getUserDbRootPassword(),
                    "-e", "MYSQL_DATABASE=" + Configuration.getUserDbName(),
                    "-e", "MYSQL_USER=" + Configuration.getUserDbUser(),
                    "-e", "MYSQL_PASSWORD=" + Configuration.getUserDbPassword(),
                    "-p", "3306:3306",
                    Const.IMAGE_MARIA,
                    "--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci");
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void initialize() {
    }

    public static void start() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Container.start(context, Const.CONTAINER_MARIA);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void stop() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Container.stop(context, Const.CONTAINER_MARIA);
        } catch (DockerExecutionException | IllegalInfraStateException e) {
            throw new InfraRuntimeException(e);
        }
    }

    public static void remove() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            Docker.call(
                    context,
                    "docker", "container", "rm", Const.CONTAINER_MARIA);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

}
