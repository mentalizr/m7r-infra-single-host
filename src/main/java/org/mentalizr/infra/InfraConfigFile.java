package org.mentalizr.infra;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.commons.M7rDirs;

import java.nio.file.Path;

public class InfraConfigFile {

    public static final String NAME = "m7r-infra.conf";

    private final Path infraConfigFile;

    public InfraConfigFile(M7rDirs m7rDirs) {
        this.infraConfigFile = m7rDirs.getInfraConfigDir().resolve(NAME);
    }

    public Path getInfraConfigFile() {
        return this.infraConfigFile;
    }

    public boolean exists() {
        return FileUtils.isExistingRegularFile(this.infraConfigFile);
    }

}
