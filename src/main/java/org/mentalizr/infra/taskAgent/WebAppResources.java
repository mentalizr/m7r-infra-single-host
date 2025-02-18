package org.mentalizr.infra.taskAgent;

import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.buildEntities.webAppResources.ResrcArchive;
import org.mentalizr.infra.buildEntities.webAppResources.WebAppResourcesChecksum;
import org.mentalizr.infra.buildEntities.webAppResources.WebAppResourcesSingleton;
import org.mentalizr.infra.docker.Docker;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAppResources {

    private static final Logger logger = LoggerFactory.getLogger(WebAppResources.class.getSimpleName());

    public static boolean isDeployed() {
        logger.debug("Call " + WebAppResources.class.getSimpleName() + ".isDeployed()");
        String checksumCode = WebAppResourcesChecksum.getBuildChecksum();
        String checksumContainer = WebAppResourcesChecksum.readFromContainer();
        boolean isDeployed = checksumCode.equals(checksumContainer);
        logger.info("Is html deployed? " + isDeployed);
        return isDeployed;
    }

    public static void deploy() {
        logger.debug("Call " + WebAppResources.class.getSimpleName() + ".deploy()");
        ResrcArchive resrcArchive = WebAppResourcesSingleton.getInstance().getResrcArchive();
        copyResourcesToContainer(resrcArchive);
        WebAppResourcesChecksum.writeToContainer();
    }

    private static void copyResourcesToContainer(ResrcArchive resrcArchive) {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        try {
            DockerCopy.copyFile(context,
                    resrcArchive.asPath(),
                    Const.CONTAINER_TOMCAT,
                    "/");
            Docker.call(context,
                    "docker",
                    "exec",
                    Const.CONTAINER_TOMCAT,
                    "tar",
                    "xzf",
                    "/" + resrcArchive.getFileName(),
                    "-C", "/man/tomcat/webapps");
            Docker.call(context,
                    "docker",
                    "exec",
                    Const.CONTAINER_TOMCAT,
                    "rm",
                    "/" + resrcArchive.getFileName());
        } catch (DockerExecutionException e) {
            throw new InfraRuntimeException("Copying resources to tomcat container failed: " + e.getMessage(), e);
        }
    }

}
