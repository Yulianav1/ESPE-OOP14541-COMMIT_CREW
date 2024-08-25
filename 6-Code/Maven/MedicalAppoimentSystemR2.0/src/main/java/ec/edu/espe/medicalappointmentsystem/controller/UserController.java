package ec.edu.espe.medicalappointmentsystem.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 *
 * @author MSI-PULSE
 */
public class UserController {

    private static final String DATABASE_NAME = "Medical_Appointment";
    private static final String COLLECTION_NAME = "User";
    private static final String URI = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";

    
    public static MongoDatabase openConnectionToMongo() {
        MongoClient mongoClient = MongoClients.create(URI);
        return mongoClient.getDatabase(DATABASE_NAME);
    }

   
    public static MongoCollection<Document> accessToCollections(MongoDatabase database, String collectionName) {
        return database.getCollection(collectionName);
    }


    public static void create(String username, String password, String doctorId) {
        MongoDatabase database = openConnectionToMongo();
        MongoCollection<Document> collection = accessToCollections(database, COLLECTION_NAME);

        String encryptedPassword = encryptPassword(password);

        Document userDocument = new Document("username", username)
                .append("password", encryptedPassword)
                .append("doctorId", doctorId);

        collection.insertOne(userDocument);
    }

    
    public static String generatePassword() {
        // Genera una contraseña aleatoria (ejemplo: usando UUID)
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8); // 8 caracteres de longitud
    }

    
    private static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    
    public boolean authenticate(String username, String password) {
        MongoDatabase database = openConnectionToMongo();
        MongoCollection<Document> collection = accessToCollections(database, COLLECTION_NAME);

        String hashedPassword = encryptPassword(password);
        Document user = collection.find(Filters.eq("username", username)).first();

        return user != null && user.getString("password").equals(hashedPassword);
    }
}