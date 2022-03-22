package org.mentalizr.infra.build;

import org.mentalizr.commons.paths.build.BuildDateFile;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.connections.ConnectionTomcat;
import org.mentalizr.infra.utils.document.Document;
import org.mentalizr.infra.utils.document.Documents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Backend {

    private static final Logger logger = LoggerFactory.getLogger(Backend.class);

    public static String getBuildDateString() {
        BuildDateFile buildDateFile = new BuildDateFile();
        if (!buildDateFile.exists())
            throw new InfraRuntimeException("File not found: [" + buildDateFile.toAbsolutePathString() + "].");
        Document document;
        try {
            document = Documents.createFrom(buildDateFile.asPath());
        } catch (IOException e) {
            throw new InfraRuntimeException("Exception on reading file: [" + buildDateFile.toAbsolutePathString() + "].");
        }
        if (document.isEmpty())
            throw new InfraRuntimeException("File is empty: [" + buildDateFile.toAbsolutePathString() + "].");
        return document.getStringByIndex(0);
    }

    public static boolean isCurrentBuildAlreadyDeployed() {
        String buildDateStringCode = getBuildDateString();
        boolean deployed = ConnectionTomcat.hasBuildDate(buildDateStringCode);
        if (deployed) {
            logger.info("Current Build is already deployed. BuildDateString = [" + buildDateStringCode + "].");
        } else {
            logger.info("Current Build not yet deployed. BuildDateString = [" + buildDateStringCode + "].");
        }
        return deployed;
    }

}
