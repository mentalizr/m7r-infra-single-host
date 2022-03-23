package org.mentalizr.infra.buildEntities.dbSchema;

import org.mentalizr.commons.paths.M7rFile;
import org.mentalizr.commons.paths.build.PersistenceRdbmsDir;

public class DbSchemaSql extends M7rFile {

    public DbSchemaSql() {
        super(new PersistenceRdbmsDir().asPath().resolve("build/sql/userdb.sql"));
    }

}
