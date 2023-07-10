package org.mentalizr.infra.buildEntities.webAppResources;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.mentalizr.commons.paths.host.hostDir.M7rHostTempDir;
import org.mentalizr.infra.InfraRuntimeException;

import java.io.IOException;

public class WebAppResourcesSingleton {

    private static WebAppResourcesSingleton instance;

    private final ResrcArchive resrcArchive;
    private final String checksum;

    public static WebAppResourcesSingleton getInstance() {
        if (instance == null) instance = new WebAppResourcesSingleton();
        return instance;
    }

    public WebAppResourcesSingleton() {
        TempDir tempDir = createTempDir();
        WebAppResourcesHelper.copyToTempDir(tempDir);
        this.resrcArchive = WebAppResourcesHelper.createTar(tempDir);
        this.checksum = WebAppResourcesHelper.computeChecksum(tempDir);
    }

    public ResrcArchive getResrcArchive() {
        return resrcArchive;
    }

    public String getChecksum() {
        return checksum;
    }

    private static TempDir createTempDir() {
        try {
            return TempDirs.createUniqueTempDirAutoRemove(new M7rHostTempDir().asPath());
        } catch (IOException e) {
            throw new InfraRuntimeException(e.getMessage(), e);
        }
    }

}
