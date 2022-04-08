package org.mentalizr.infra.buildEntities.ports;

import de.arthurpicht.utils.io.net.Sockets;

public class PortNginx {

    public static boolean isListening() {
        return Sockets.isPortListening("localhost", 443);
    }

}
