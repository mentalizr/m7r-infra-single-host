package org.mentalizr.infra.buildEntities.webAppResources;

import de.arthurpicht.utils.io.tempDir.TempDir;
import org.mentalizr.commons.paths.M7rFile;

public class ResrcArchive extends M7rFile {

    public ResrcArchive(TempDir tempDir) {
        super(tempDir.asPath().resolve("resrc.tar.gz"));
    }

}
