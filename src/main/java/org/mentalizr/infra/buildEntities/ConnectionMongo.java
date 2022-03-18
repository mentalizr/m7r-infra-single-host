package org.mentalizr.infra.buildEntities;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.mentalizr.backend.config.Configuration;
import org.mentalizr.infra.InfraRuntimeException;

public class ConnectionMongo {

    public static final String USERNAME = Configuration.getDocumentDbUser();
    public static final String PASSWORD = Configuration.getDocumentDbPassword();
    public static final String HOST = "localhost";
    public static final String DATABASE = Configuration.getDocumentDbName();

    public static void probe() {

        try {

            // This is a hack! TODO: Rework after Upgrade to current mongoDB java driver.

            ConnectionString connectionString = new ConnectionString(
                    "mongodb://" + USERNAME + ":" + PASSWORD + "@" + HOST + "/" + DATABASE);

            MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
//                    .applyToSocketSettings(builder -> {
//                        builder.connectTimeout(3, TimeUnit.SECONDS);
//                        builder.applyConnectionString(connectionString);
//                    })
                    .retryWrites(true)
                    .build();
            MongoClient mongoClient = MongoClients.create(mongoClientSettings);
            MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE);

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
