package org.mentalizr.infra.build;

import de.arthurpicht.utils.struct.document.Document;
import de.arthurpicht.utils.struct.document.Documents;
import org.mentalizr.commons.paths.build.BuildIdFile;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.connections.ConnectionTomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Backend {

    private static final Logger logger = LoggerFactory.getLogger(Backend.class);

    public static String getBuildIdString() {
        BuildIdFile buildIdFile = new BuildIdFile();
        if (!buildIdFile.exists())
            throw new InfraRuntimeException("File not found: [" + buildIdFile.toAbsolutePathString() + "].");
        Document document;
        try {
            document = Documents.createFrom(buildIdFile.asPath());
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on reading file: [" + buildIdFile.toAbsolutePathString() + "].");
        }
        if (document.isEmpty())
            throw new InfraRuntimeException("File is empty: [" + buildIdFile.toAbsolutePathString() + "].");
        return document.getStringByIndex(0);
    }

    public static boolean isCurrentBuildAlreadyDeployed() {
        String buildIdString = getBuildIdString();
        boolean deployed = ConnectionTomcat.hasBuildId(buildIdString);
        if (deployed) {
            logger.info("Current Build is already deployed. BuildId = [" + buildIdString + "].");
        } else {
            logger.info("Current Build not yet deployed. BuildId = [" + buildIdString + "].");
        }
        return deployed;
    }

}
