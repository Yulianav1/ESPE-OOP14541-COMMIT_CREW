package ec.edu.espe.medicalappointmentsystem.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import static ec.edu.espe.medicalappointmentsystem.util.MongoDBConnection.getDatabase;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Domenica Villagomez, CommitCrew, DCCO-ESPE
 */
public class PatientController {

    private static final String URI = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
    private static final String DATABASE_NAME = "Medical_Appointment";
    private static final String COLLECTION_NAME = "Patient";

    public static boolean create(Patient patient) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document dataOfPatient = new Document()
                    .append("id", patient.getId())
                    .append("name", patient.getName())
                    .append("age", patient.getAge())
                    .append("email", patient.getEmail())
                    .append("cellphone", patient.getCellphone());

            collection.insertOne(dataOfPatient);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Abir conexión con mongoDB
    public static MongoDatabase openConnectionToMongo(String uri) {
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase dataBase = mongoClient.getDatabase("Medical_Appointment");

        return dataBase;
    }

    public static void insertOneData(Document data, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertOne(data);
    }

    public static void insertMoreThanOneData(List<Document> listOfData, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertMany(listOfData);
    }

    public static boolean exists(String patientId) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document patient = collection.find(Filters.eq("id", patientId)).first();
            return patient != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Patient patient) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Bson filter = Filters.eq("id", patient.getId());
            Bson updates = Updates.combine(
                    Updates.set("name", patient.getName()),
                    Updates.set("age", patient.getAge()),
                    Updates.set("email", patient.getEmail()),
                    Updates.set("cellphone", patient.getCellphone())
            );
            UpdateOptions options = new UpdateOptions().upsert(true);

            collection.updateOne(filter, updates, options);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Patient getId(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static List<Patient> getPatients() {
        String collectionName = "Patient";
        List<Patient> patients = new ArrayList<>();
        MongoDatabase database = getDatabase();
        MongoCollection<Document> collection = database.getCollection(collectionName);

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Patient patient = new Patient(
                        doc.getString("id"),
                        doc.getString("email"),
                        doc.getString("cellphone"),
                        doc.getString("name")
                );
                patients.add(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public static Patient getPatientById(String id) {
        String collectionName = "Patient";
        MongoDatabase database = getDatabase();
        MongoCollection<Document> collection = database.getCollection(collectionName);

        try {
            Document filter = new Document("id", id);
            Document doc = collection.find(filter).first();

            if (doc != null) {
                return new Patient(
                        doc.getString("id"),
                        doc.getString("name"),
                        doc.getInteger("age"),
                        doc.getString("email"),
                        doc.getString("cellphone")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
