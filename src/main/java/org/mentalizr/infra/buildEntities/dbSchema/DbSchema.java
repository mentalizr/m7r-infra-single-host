package org.mentalizr.infra.buildEntities.dbSchema;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.buildEntities.connections.ConnectionMaria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.List;

public class DbSchema {

    private static final Logger logger = LoggerFactory.getLogger(DbSchema.class.getSimpleName());

    public static boolean hasDbCreatedTables() {
        try (Connection connection = ConnectionMaria.getConnectionToDbAsRoot()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "user", null);
            return tables.next();
        } catch (SQLException e) {
            throw new InfraRuntimeException("Exception on fetching db meta data: " + e.getMessage(), e);
        }
    }

    public static void deploy() {

        List<String> strings = readDbSchemaSqlFile();

        Connection connection = ConnectionMaria.getConnectionToDbAsRoot();

        logger.info("Initialize DB schema on maria.");
        for (String sql : strings) {
            if (Strings.isUnspecified(sql)) continue;
            logger.debug("SQL> " + sql);
            executeSQLStatement(connection, sql);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new InfraRuntimeException("Error on closing connection: " + e.getMessage(), e);
        }
    }

    private static void executeSQLStatement(Connection connection, String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new InfraRuntimeException("Error on executing SQL: " + e.getMessage(), e);
        }
    }

    private static List<String> readDbSchemaSqlFile() {
        DbSchemaSql dbSchemaSql = new DbSchemaSql();
        try {
            return Files.readAllLines(dbSchemaSql.asPath());
        } catch (IOException e) {
            throw new InfraRuntimeException("Error reading DB schema SQL file: " + e.getMessage(), e);
        }

    }

}
