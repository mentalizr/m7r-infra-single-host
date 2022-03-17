package org.mentalizr.infra.utils;

import java.io.IOException;
import java.net.Socket;

public class IOUtils {

    public static boolean isPortListening(int port) {
        try (Socket socket = new Socket("localhost", port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
