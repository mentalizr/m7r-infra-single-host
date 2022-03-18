package org.mentalizr.infra.docker.m7r;

import org.mentalizr.commons.dirs.host.ContentDir;
import org.mentalizr.commons.dirs.host.GitReposDir;
import org.mentalizr.commons.dirs.host.hostDir.TomcatLogDir;
import org.mentalizr.infra.*;
import org.mentalizr.infra.buildEntities.initFiles.TomcatContextXml;
import org.mentalizr.infra.docker.Container;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.processExecutor.ProcessResultCollection;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

public class M7rContainerTomcat {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);

    public static void create() {
        if (exists())
            throw new InfraRuntimeException("Cannot create container [" + Const.CONTAINER_TOMCAT + "]." +
                    " Already existing.");

        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        Path imageBaseTempDir = GitReposDir.getInstance().asPath().resolve("core/m7r-img-base-tmp");
        ProcessResultCollection result;
        try {
            result = Docker.call(
                    context,
                    "docker", "create",
                    "--name", Const.CONTAINER_TOMCAT,
                    "--network", Const.NETWORK,
                    "--network-alias", "tomcat",
                    "-e", "TIME_ZONE=\"Europe/Berlin\"",
                    "--mount", "source=" + Const.VOLUME_TOMCAT +",target=/man/tomcat/webapps",
                    "--mount", "type=bind,source=" + ContentDir.getInstance().toAbsolutePathString() + ",target=/mentalizr/content",
                    "--mount", "type=bind,source=" + imageBaseTempDir.toAbsolutePath() + ",target=/mentalizr/img-base-tmp",
                    "--mount", "type=bind,source=" + TomcatLogDir.createInstance().toAbsolutePathString() + ",target=/man/tomcat/logs",
                    "-p", "8080:8080",
                    Const.IMAGE_TOMCAT);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
        if (result.isFail()) throw M7rContainer.createInfraRuntimeException(result);
    }

    public static void initialize() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();

        TomcatContextXml tomcatContextXml = TomcatContextXml.getInstanceFromConfiguration();
        String messageHeader = "Copy configuration file [" + tomcatContextXml.getFileName() + "] to [" + Const.CONTAINER_MONGO + "]:";
        if (context.isVerbose()) {
            System.out.println(messageHeader);
            System.out.println(tomcatContextXml.getContent());
        }
        logger.debug(messageHeader);
        logger.debug(tomcatContextXml.getContent());

        try {
            Path tomcatContextXMLinTempDir = tomcatContextXml.writeToHostTempDir();
            DockerCopy.copyFileWithPreservedRights(context, tomcatContextXMLinTempDir, Const.CONTAINER_TOMCAT, "/man/tomcat/conf/");
        } catch (DockerExecutionException | IOException e) {
            throw new InfraRuntimeException(e.getMessage(), e);
        }
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

    public static void stop() {
        M7rContainer.stop(Const.CONTAINER_TOMCAT);
    }

    public static boolean isStopped() {
        return M7rContainer.isStopped(Const.CONTAINER_TOMCAT);
    }

    public static void remove() {
        M7rContainer.remove(Const.CONTAINER_TOMCAT);
    }

}
