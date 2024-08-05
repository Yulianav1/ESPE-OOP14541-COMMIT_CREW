package ec.edu.espe.medicalappointmentsystem.util;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {
    private static final Logger LOGGER = Logger.getLogger(DataBase.class.getName());
    private static final String URI = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
    private static final String DATABASE_NAME = "Medical_Appointment";
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        try {
            initializeConnection();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize database connection", e);
        }
    }

    // Método para inicializar la conexión a MongoDB
    private static void initializeConnection() {
        if (mongoClient == null) {
            LOGGER.info("Initializing MongoDB connection...");
            ConnectionString connectionString = new ConnectionString(URI);
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToSocketSettings(builder -> 
                    builder.connectTimeout(5000, TimeUnit.MILLISECONDS))
                .build();
            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(DATABASE_NAME);
            LOGGER.info("MongoDB connection initialized successfully.");
        }
    }

    // Método para obtener la colección de doctores
    public static MongoCollection<Document> getDoctorCollection() {
        ensureConnection();
        return database.getCollection("Doctor");
    }

    // Método para obtener la colección de citas
    public static MongoCollection<Document> getAppointmentCollection() {
        ensureConnection();
        return database.getCollection("Appointment");
    }

    // Método para asegurar que la conexión esté inicializada
    private static void ensureConnection() {
        if (database == null) {
            initializeConnection();
        }
    }

    // Método para cerrar la conexión a MongoDB
    public static void closeConnection() {
        if (mongoClient != null) {
            LOGGER.info("Closing MongoDB connection...");
            mongoClient.close();
            mongoClient = null;
            database = null;
            LOGGER.info("MongoDB connection closed.");
        }
    }
}