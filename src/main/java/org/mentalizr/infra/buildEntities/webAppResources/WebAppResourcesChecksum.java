package org.mentalizr.infra.buildEntities.webAppResources;

import org.mentalizr.infra.buildEntities.ChecksumExchanger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAppResourcesChecksum {

    private static final Logger logger = LoggerFactory.getLogger(WebAppResourcesChecksum.class.getSimpleName());

    private static final String fileName = "resrc.checksum";

    public static String getBuildChecksum() {
        return WebAppResourcesSingleton.getInstance().getChecksum();
    }

    public static void writeToContainer() {
        String checksum = WebAppResourcesSingleton.getInstance().getChecksum();
        ChecksumExchanger.copyToContainer(fileName, checksum);
    }

    public static String readFromContainer() {
        return ChecksumExchanger.readFromContainer(fileName);
    }

}
