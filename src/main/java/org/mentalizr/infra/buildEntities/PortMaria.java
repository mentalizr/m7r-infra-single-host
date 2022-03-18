package org.mentalizr.infra.buildEntities;

import org.mentalizr.infra.utils.IOUtils;

public class PortMaria {

    public static boolean isListening() {
        return IOUtils.isPortListening(3306);
    }

}
