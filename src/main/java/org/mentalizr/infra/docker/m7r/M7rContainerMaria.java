package org.mentalizr.infra.docker.m7r;

import de.arthurpicht.processExecutor.ProcessResultCollection;
import org.mentalizr.backend.config.infraUser.InfraUserConfiguration;
import org.mentalizr.infra.*;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M7rContainerMaria {

    private static final Logger logger = LoggerFactory.getLogger(Const.DOCKER_LOGGER);

    public static void create() {
        if (exists())
            throw new InfraRuntimeException("Cannot create container [" + Const.CONTAINER_MARIA + "]." +
                    " Already existing.");

        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        ProcessResultCollection result;
        try {
            result = Docker.call(
                    context,
                    "docker", "create",
                    "--name", Const.CONTAINER_MARIA,
                    "--network", Const.NETWORK,
                    "--network-alias", "maria",
                    "--mount", "source=" + Const.VOLUME_MARIA +",target=/var/lib/mysql",
                    "-e", "MYSQL_ROOT_PASSWORD=" + InfraUserConfiguration.getUserDbRootPassword(),
                    "-e", "MYSQL_DATABASE=" + InfraUserConfiguration.getUserDbName(),
                    "-e", "MYSQL_USER=" + InfraUserConfiguration.getUserDbUser(),
                    "-e", "MYSQL_PASSWORD=" + InfraUserConfiguration.getUserDbPassword(),
                    "-p", "3306:3306",
                    Const.IMAGE_MARIA,
                    "--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci");
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
        if (result.isFail()) throw M7rContainer.createInfraRuntimeException(result);
    }

    public static void initialize() {
    }

    public static boolean exists() {
        return M7rContainer.exists(Const.CONTAINER_MARIA);
    }

    public static void start() {
        M7rContainer.start(Const.CONTAINER_MARIA);
    }

    public static boolean isRunning() {
        return M7rContainer.isRunning(Const.CONTAINER_MARIA);
    }

    public static void stop() {
        M7rContainer.stop(Const.CONTAINER_MARIA);
    }

    public static boolean isStopped() {
        return M7rContainer.isStopped(Const.CONTAINER_MARIA);
    }

    public static void remove() {
        M7rContainer.remove(Const.CONTAINER_MARIA);
    }

}
