package org.mentalizr.infra.buildEntities.initFiles.maria;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.commons.paths.host.GitReposDir;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.initFiles.InitFile;
import org.mentalizr.infra.utils.document.Document;
import org.mentalizr.infra.utils.document.Documents;

import java.io.IOException;
import java.nio.file.Path;

public class UserDbSql implements InitFile {

    private final Document document;

    public static UserDbSql getInstance() {
        return new UserDbSql();
    }

    private UserDbSql() {
        Path file = GitReposDir.getInstance().asPath().resolve("core/m7r-persistence-rdbms/build/sql/userdb.sql");
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
