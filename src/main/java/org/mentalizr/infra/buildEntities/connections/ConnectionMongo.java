package org.mentalizr.infra.buildEntities.connections;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.mentalizr.backend.config.infraUser.InfraUserConfiguration;
import org.mentalizr.infra.InfraRuntimeException;
import org.mentalizr.infra.appInit.ApplicationContext;

public class ConnectionMongo {

    public static void probe() {

        InfraUserConfiguration infraUserConfiguration = ApplicationContext.getInfraUserConfiguration();
        String userName = infraUserConfiguration.getDocumentDbUser();
        String password = infraUserConfiguration.getDocumentDbPassword();
        String host = "localhost";
        String database = infraUserConfiguration.getDocumentDbName();

        try {

            // This is a hack! TODO: Rework after Upgrade to current mongoDB java driver.

            ConnectionString connectionString = new ConnectionString(
                    "mongodb://" + userName + ":" + password + "@" + host + "/" + database);

            MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
//                    .applyToSocketSettings(builder -> {
//                        builder.connectTimeout(3, TimeUnit.SECONDS);
//                        builder.applyConnectionString(connectionString);
//                    })
                    .retryWrites(true)
                    .build();
            MongoClient mongoClient = MongoClients.create(mongoClientSettings);
            MongoDatabase mongoDatabase = mongoClient.getDatabase(database);

            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("testCollection");
            Document queryDocument =
                    new Document("key", "value");

            FindIterable<Document> iterable = mongoCollection.find(queryDocument);

            for (Document document : iterable) {
                if (document != null) break;
            }

        } catch (RuntimeException e) {
            throw new InfraRuntimeException("Client connection to mongoDB failed.");
        }
    }

    public static boolean connectionSuccessful() {
        try {
            probe();
            return true;
        } catch (InfraRuntimeException e) {
            return false;
        }
    }

}
