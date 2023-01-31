package org.mentalizr.infra.buildEntities;

import org.mentalizr.commons.paths.host.hostDir.TomcatLogDir;
import org.mentalizr.infra.InfraRuntimeException;

import java.io.IOException;

public class LogDirs {

    public static boolean existsAllLogDirs() {
        return new TomcatLogDir().exists();
    }

    public static void createAllLogDirs() {
        try {
            new TomcatLogDir().create();
        } catch (IOException e) {
            throw new InfraRuntimeException("Logfiles could not be created: " + e.getMessage(), e);
        }
    }

}
