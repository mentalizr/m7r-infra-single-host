package org.mentalizr.infra.buildEntities.ports;

import org.mentalizr.infra.utils.IOUtils;

public class PortMongo {

    public static boolean isListening() {
        return IOUtils.isPortListening(27017);
    }

}
