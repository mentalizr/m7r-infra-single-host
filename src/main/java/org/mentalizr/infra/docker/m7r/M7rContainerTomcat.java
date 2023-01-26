package org.mentalizr.infra.docker.m7r;

import de.arthurpicht.processExecutor.ProcessResultCollection;
import de.arthurpicht.taskRunner.task.TaskPreconditionException;
import org.mentalizr.commons.paths.build.M7rWarFile;
import org.mentalizr.commons.paths.container.TomcatContainerContentDir;
import org.mentalizr.commons.paths.container.TomcatContainerImgBaseTmpDir;
import org.mentalizr.commons.paths.container.TomcatContainerM7rHostConfigDir;
import org.mentalizr.commons.paths.container.TomcatContainerWebAppsDir;
import org.mentalizr.commons.paths.host.ContentDir;
import org.mentalizr.commons.paths.host.GitReposDir;
import org.mentalizr.commons.paths.host.hostDir.M7rHostConfigDir;
import org.mentalizr.commons.paths.host.hostDir.M7rHostDir;
import org.mentalizr.commons.paths.host.hostDir.TomcatLogDir;
import org.mentalizr.infra.*;
import org.mentalizr.infra.buildEntities.initFiles.tomcat.TomcatContextXml;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class M7rContainerTomcat {

    private static final Logger logger = LoggerFactory.getLogger(Const.DOCKER_LOGGER);

    public static void create() {
        if (exists())
            throw new InfraRuntimeException("Cannot create container [" + Const.CONTAINER_TOMCAT + "]." +
                    " Already existing.");

        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        Path imageBaseTempDir = GitReposDir.createInstance().asPath().resolve("core/m7r-img-base-tmp");
        ProcessResultCollection result;
        try {
            result = Docker.call(
                    context,
                    "docker", "create",
                    "--name", Const.CONTAINER_TOMCAT,
                    "--network", Const.NETWORK,
                    "--network-alias", "tomcat",
                    "-e", "TIME_ZONE=\"Europe/Berlin\"",
                    "--mount", "source=" + Const.VOLUME_TOMCAT +",target=" + new TomcatContainerWebAppsDir().toAbsolutePathString(),
                    "--mount", "type=bind,source=" + ContentDir.createInstance().toAbsolutePathString() + ",target=" + new TomcatContainerContentDir().toAbsolutePathString(),
                    "--mount", "type=bind,source=" + imageBaseTempDir.toAbsolutePath() + ",target=" + new TomcatContainerImgBaseTmpDir().toAbsolutePathString(),
                    "--mount", "type=bind,source=" + TomcatLogDir.createInstance().toAbsolutePathString() + ",target=/man/tomcat/logs",
                    "--mount", "type=bind,source=" + M7rHostConfigDir.createInstance().toAbsolutePathString() + ",target=" + TomcatContainerM7rHostConfigDir.asAbsolutePathString(),
                    "-p", "8080:8080",
                    Const.IMAGE_TOMCAT);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
        if (result.isFail()) throw M7rContainer.createInfraRuntimeException(result);
    }

    public static void initialize() {
        M7rContainer.copyInitFileToContainer(
                TomcatContextXml.getInstanceFromConfiguration(),
                Const.CONTAINER_TOMCAT,
                "/man/tomcat/conf/");
    }

    public static boolean exists() {
        return M7rContainer.exists(Const.CONTAINER_TOMCAT);
    }

    public static void start() {
        M7rContainer.start(Const.CONTAINER_TOMCAT);
    }

    public static boolean isRunning() {
        return M7rContainer.isRunning(Const.CONTAINER_TOMCAT);
    }

    public static void assertIsRunning() throws TaskPreconditionException {
        if (!isRunning()) throw new TaskPreconditionException("[" + Const.CONTAINER_TOMCAT + "] not running.");
    }

    public static void stop() {
        M7rContainer.stop(Const.CONTAINER_TOMCAT);
    }

    public static boolean isStopped() {
        return M7rContainer.isStopped(Const.CONTAINER_TOMCAT);
    }

    public static void remove() {
        M7rContainer.remove(Const.CONTAINER_TOMCAT);
    }

    public static void deployWar() {
        M7rWarFile m7rWarFile = M7rWarFile.createInstance();
        DockerExecutionContext context = ExecutionContext.getDockerExecutionContext();
        try {
            DockerCopy.copyFile(context, m7rWarFile.asPath(), Const.CONTAINER_TOMCAT, "/man/tomcat/webapps/");
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
    }

}
