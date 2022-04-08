package org.mentalizr.infra.buildEntities.ports;

import de.arthurpicht.utils.io.net.Sockets;

public class PortMongo {

    public static boolean isListening() {
        return Sockets.isPortListening("localhost", 27017);
    }

}
