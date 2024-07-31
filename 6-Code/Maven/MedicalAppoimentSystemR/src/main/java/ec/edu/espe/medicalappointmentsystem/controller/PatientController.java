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
import java.net.URI;
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
                    .append("Nombre", patient.getName())
                    .append("Edad", patient.getAge())
                    .append("Email", patient.getEmail())
                    .append("Teléfono", patient.getCellphone());

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

    //Acceso a colecciones
    public static MongoCollection<Document> accessToCollections(MongoDatabase dataBase, String collection) {
        MongoCollection<Document> mongoCollection = dataBase.getCollection(collection);
        return mongoCollection;
    }

    //Tipo de ingreso de datos
    public static void insertOneData(Document data, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertOne(data);
    }

    public static void insertMoreThanOneData(List<Document> listOfData, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertMany(listOfData);
    }

    //Obtención de datos
    public static void getAllCollection(MongoCollection<Document> mongoCollection) {
        //Si solo busco en base a un solo dato 
        Document findDocument = new Document("male", true);
        //Si quiero todo el documento:
        //Document findDocument = new Document();

        MongoCursor<Document> resultDocument = mongoCollection.find(findDocument).iterator();

        System.out.println("***************************************");
        System.out.println("People male");
        System.out.println("***************************************");
        while (resultDocument.hasNext()) {
            System.out.println(resultDocument.next().getString("name"));
        }

        //return resultDocument;
    }

    //Actualización de documentos
    public static void editDocuments(String key, String data, String newData, MongoCollection<Document> mongoCollection) {
        Document findDocument = new Document(key, data);

        Document updateDocument = new Document("$set", new Document(key, newData));

        mongoCollection.findOneAndUpdate(findDocument, updateDocument);
    }

    //Eliminar documentos
    public static void deleteDocuments(String key, String data, MongoCollection<Document> mongoCollection) {
        //TODO: Combinar con método de obtención de datos
        Document findDocument = new Document("male", true);
        mongoCollection.findOneAndDelete(findDocument);
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
                    Updates.set("Nombre", patient.getName()),
                    Updates.set("Edad", patient.getAge()),
                    Updates.set("Email", patient.getEmail()),
                    Updates.set("Teléfono", patient.getCellphone())
            );
            UpdateOptions options = new UpdateOptions().upsert(true);

            collection.updateOne(filter, updates, options);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
