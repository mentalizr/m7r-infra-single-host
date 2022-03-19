package org.mentalizr.infra.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class IOUtils {

    public static boolean isPortListening(int port) {
        try (Socket socket = new Socket("localhost", port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = IOUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found in ressources: [" + fileName + "].");
        } else {
            return inputStream;
        }
    }


}
