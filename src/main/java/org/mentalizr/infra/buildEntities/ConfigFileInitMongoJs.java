package org.mentalizr.infra.buildEntities;

import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.utils.FileHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigFileInitMongoJs {

    private final String dbName;
    private final String userName;
    private final String password;

    public ConfigFileInitMongoJs(String dbName, String username, String password) {
        this.dbName = dbName;
        this.userName = username;
        this.password = password;
    }

    public static ConfigFileInitMongoJs getInstanceFromConfiguration() {
        String dbName = Configuration.getDocumentDbName();
        String userName = Configuration.getDocumentDbName();
        String password = Configuration.getDocumentDbPassword();
        return new ConfigFileInitMongoJs(dbName, userName, password);
    }

    public String getFileName() {
        return "init-mongo.js";
    }

    public String getContent() {

        return "" +
                "db = db.getSiblingDB('" + this.dbName + "')\n" +
                "\n" +
                "db.createUser(\n" +
                "  {\n" +
                "    user: \"" + this.userName + "\",\n" +
                "    pwd: \"" + this.password + "\",\n" +
                "    roles: [ { role: \"readWrite\", db: \"" + this.dbName + "\" } ]\n" +
                "  }\n" +
                ")\n" +
                "\n" +
                "db.testCollection.insert({name:'Hans Wurst'});\n";
    }

    public Path writeToM7rTempDir() throws IOException {
        return FileHelper.writeToM7rTempDir(getFileName(), getContent());
    }

}
