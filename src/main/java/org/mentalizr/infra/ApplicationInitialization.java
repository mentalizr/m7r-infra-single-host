package org.mentalizr.infra;

import org.mentalizr.commons.dirs.host.hostDir.M7rHostLogDir;
import org.mentalizr.infra.utils.LoggerUtils;

import java.io.IOException;

public class ApplicationInitialization {

    public static void execute() {
        createLogDir();
        LoggerUtils.initialize();
    }

    private static void createLogDir() {
        M7rHostLogDir m7rHostLogDir = M7rHostLogDir.createInstance();
        if (!m7rHostLogDir.exists()) {
            try {
                m7rHostLogDir.create();
            } catch (IOException e) {
                throw new RuntimeException("Application initialization failed. Could not create directory ["
                        + m7rHostLogDir.toAbsolutePathString() + "]");
            }
        }
    }

}
