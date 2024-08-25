
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
/**
 *
 * @author MSI-PULSE
 */
public class AdminController {

    private static final String DATABASE_NAME = "Medical_Appointment";
    private static final String COLLECTION_NAME = "user";
    private static final String URI = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "P@ssw0rd2024!"; // Contraseña predeterminada

    public AdminController() {
        MongoDatabase database = openConnectionToMongo();
        MongoCollection<Document> collection = accessToCollections(database, COLLECTION_NAME);

        
        if (collection.find(Filters.eq("username", ADMIN_USERNAME)).first() == null) {
            createAdminUser(collection);
        }
    }

    
    public static MongoDatabase openConnectionToMongo() {
        MongoClient mongoClient = MongoClients.create(URI);
        return mongoClient.getDatabase(DATABASE_NAME);
    }

   
    public static MongoCollection<Document> accessToCollections(MongoDatabase database, String collectionName) {
        return database.getCollection(collectionName);
    }

    
    private void createAdminUser(MongoCollection<Document> collection) {
        String hashedPassword = hashPassword(ADMIN_PASSWORD);
        Document adminUser = new Document("username", ADMIN_USERNAME)
                .append("password", hashedPassword);
        collection.insertOne(adminUser);
    }

    
    public boolean authenticate(String username, String password) {
        MongoDatabase database = openConnectionToMongo();
        MongoCollection<Document> collection = accessToCollections(database, COLLECTION_NAME);

        String hashedPassword = hashPassword(password);
        Document user = collection.find(Filters.eq("username", username)).first();

        return user != null && user.getString("password").equals(hashedPassword);
    }

    
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
