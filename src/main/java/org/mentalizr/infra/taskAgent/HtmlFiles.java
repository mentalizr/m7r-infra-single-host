package org.mentalizr.infra.taskAgent;

import org.mentalizr.infra.Const;
import org.mentalizr.infra.DockerExecutionException;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.appInit.ApplicationContext;
import org.mentalizr.infra.buildEntities.html.HtmlChecksum;
import org.mentalizr.infra.buildEntities.html.HtmlFilesRegistry;
import org.mentalizr.infra.docker.DockerCopy;
import org.mentalizr.infra.docker.DockerExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

public class HtmlFiles {

    private static final Logger logger = LoggerFactory.getLogger(HtmlFiles.class.getSimpleName());

    public static void deploy() {
        logger.info("Deploy html files.");
        logger.debug("Call " + HtmlFiles.class.getSimpleName() + ".deploy()");
        copyHtmlFilesToContainer();
        HtmlChecksum.writeToContainer();
    }

    public static boolean isDeployed() {
        logger.debug("Call " + HtmlFiles.class.getSimpleName() + ".isDeployed()");
        String checksumCode = HtmlChecksum.forHtmlFiles();
        String checksumContainer = HtmlChecksum.readFromContainer();
        boolean isDeployed = checksumCode.equals(checksumContainer);
        logger.info("Is html deployed? " + isDeployed);
        return isDeployed;
    }

    public static void writeChecksumToContainer() {
        HtmlChecksum.writeToContainer();
    }

    private static void copyHtmlFilesToContainer() {
        DockerExecutionContext context = ApplicationContext.getDockerExecutionContext();
        List<Path> htmlFileList = HtmlFilesRegistry.getInstance().getHtmlFileList();
        for (Path file : htmlFileList) {
            logger.info("Copy htmlFile [" + file.toAbsolutePath() + "] to WEB-INF dir in tomcat docker container");
            try {
                DockerCopy.copyFile(
                        context,
                        file,
                        Const.CONTAINER_TOMCAT,
                        "/man/tomcat/webapps/m7r/WEB-INF/");
            } catch (DockerExecutionException e) {
                throw new InfraRuntimeException("Exception on copying html file to tomcat container: "
                        + e.getMessage(), e);
            }
        }
    }

}
