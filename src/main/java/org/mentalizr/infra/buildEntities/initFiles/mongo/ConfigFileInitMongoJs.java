package org.mentalizr.infra.buildEntities.initFiles.mongo;

import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.buildEntities.initFiles.InitFile;

public class ConfigFileInitMongoJs implements InitFile {

    private final String adminUsername;
    private final String adminPassword;
    private final String dbName;
    private final String userName;
    private final String password;

    public static ConfigFileInitMongoJs getInstanceFromConfiguration() {
        String adminUsername = Configuration.getDocumentDbAdminName();
        String adminPassword = Configuration.getDocumentDbAdminPassword();
        String dbName = Configuration.getDocumentDbName();
        String userName = Configuration.getDocumentDbUser();
        String password = Configuration.getDocumentDbPassword();
        return new ConfigFileInitMongoJs(adminUsername, adminPassword, dbName, userName, password);
    }

    public ConfigFileInitMongoJs(String adminUsername, String adminPassword, String dbName, String username, String password) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.dbName = dbName;
        this.userName = username;
        this.password = password;
    }

    public String getFileName() {
        return "init-mongo.js";
    }

    public String getContent() {
        return "\n" +
                "db.auth('" + this.adminUsername + "', '" + this.adminPassword + "')\n" +
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
                "db.testCollection.insert({name:'Dummy'});\n";
    }

}
