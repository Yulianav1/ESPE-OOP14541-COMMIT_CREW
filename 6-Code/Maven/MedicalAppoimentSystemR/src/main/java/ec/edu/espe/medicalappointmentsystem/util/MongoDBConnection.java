package ec.edu.espe.medicalappointmentsystem.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private MongoDBConnection() {
    }

    public static synchronized MongoDatabase getDatabase() {
        if (database == null) {
            String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("Medical_Appointment");
        }
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
        }
    }
}
