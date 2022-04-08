package org.mentalizr.infra.buildEntities.initFiles.maria;

import de.arthurpicht.utils.io.nio2.FileUtils;
import de.arthurpicht.utils.struct.document.DocumentInterface;
import de.arthurpicht.utils.struct.document.Documents;
import org.mentalizr.commons.paths.host.GitReposDir;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.initFiles.InitFile;

import java.io.IOException;
import java.nio.file.Path;

public class UserDbSql implements InitFile {

    private final DocumentInterface document;

    public static UserDbSql getInstance() {
        return new UserDbSql();
    }

    private UserDbSql() {
        Path file = GitReposDir.createInstance().asPath().resolve("core/m7r-persistence-rdbms/build/sql/userdb.sql");
        if (!FileUtils.isExistingRegularFile(file)) throw new InfraRuntimeException("File not found. [" + file.toAbsolutePath() + "]");
        try {
            this.document = Documents.createFrom(file);
        } catch (IOException e) {
            throw new InfraRuntimeException(e);
        }
    }

    @Override
    public String getFileName() {
        return "userdb.sql";
    }

    @Override
    public String getContent() {
        return this.document.asString();
    }
}
