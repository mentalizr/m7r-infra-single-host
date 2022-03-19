package org.mentalizr.infra.docker.m7r;

import org.mentalizr.commons.dirs.host.ContentDir;
import org.mentalizr.commons.dirs.host.GitReposDir;
import org.mentalizr.commons.dirs.host.hostDir.CertsDir;
import org.mentalizr.commons.dirs.host.hostDir.TomcatLogDir;
import org.mentalizr.infra.ApplicationContext;
import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.initFiles.TomcatContextXml;
import org.mentalizr.infra.buildEntities.initFiles.nginx.IndexHtml;
import org.mentalizr.infra.buildEntities.initFiles.nginx.LocalDevConf;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.mentalizr.infra.processExecutor.ProcessResultCollection;
import org.mentalizr.infra.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

public class M7rContainerNginx {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.DOCKER_LOGGER);

    public static void create() {
        if (exists())
            throw new InfraRuntimeException("Cannot create container [" + Const.CONTAINER_NGINX + "]." +
                    " Already existing.");

        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();

        ProcessResultCollection result;
        try {
            result = Docker.call(
                    context,
                    "docker", "create",
                    "--name", Const.CONTAINER_NGINX,
                    "--network", Const.NETWORK,
                    "-v", CertsDir.createInstance().toAbsolutePathString() + ":/etc/nginx/certs",
                    "-p", "443:443",
                    Const.IMAGE_NGINX);
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException(e);
        }
        if (result.isFail()) throw M7rContainer.createInfraRuntimeException(result);
    }

    public static void initialize() {
        M7rContainer.copyInitFileToContainer(
                LocalDevConf.getInstance(),
                Const.CONTAINER_NGINX,
                "/etc/nginx/conf.d/");
        M7rContainer.copyInitFileToContainer(
                IndexHtml.getInstance(),
                Const.CONTAINER_NGINX,
                "/usr/share/nginx/html/");
    }

    public static boolean exists() {
        return M7rContainer.exists(Const.CONTAINER_NGINX);
    }

    public static void start() {
        M7rContainer.start(Const.CONTAINER_NGINX);
    }

    public static boolean isRunning() {
        return M7rContainer.isRunning(Const.CONTAINER_NGINX);
    }

    public static void stop() {
        M7rContainer.stop(Const.CONTAINER_NGINX);
    }

    public static boolean isStopped() {
        return M7rContainer.isStopped(Const.CONTAINER_NGINX);
    }

    public static void remove() {
        M7rContainer.remove(Const.CONTAINER_NGINX);
    }

}
