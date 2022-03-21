package org.mentalizr.infra.buildEntities.ports;

import org.mentalizr.infra.utils.IOUtils;

public class PortNginx {

    public static boolean isListening() {
        return IOUtils.isPortListening(443);
    }

}
