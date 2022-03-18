package org.mentalizr.infra.buildEntities;

import org.mentalizr.commons.dirs.host.hostDir.TomcatLogDir;
import org.mentalizr.infra.InfraRuntimeException;

import java.io.IOException;

public class LogDirs {

    public static boolean existsAllLogDirs() {
        return TomcatLogDir.createInstance().exists();
    }

    public static void createAllLogDirs() {
        try {
            TomcatLogDir.createInstance().create();
        } catch (IOException e) {
            throw new InfraRuntimeException("Logfiles could not be created: " + e.getMessage(), e);
        }
    }

}
