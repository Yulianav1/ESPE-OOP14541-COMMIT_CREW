package ec.edu.espe.medicalappointmentsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import ec.edu.espe.medicalappointmentsystem.util.IdValidator;
import static ec.edu.espe.medicalappointmentsystem.util.MongoDBConnection.getDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author Domenica Villagomez, CommitCrew, DCCO-ESPE
 */
public class DoctorController {

    private List<Doctor> doctorList;
    private static final String FILE_NAME = "Doctors.json";

    public static boolean create(Doctor doctor) {

        String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";

        MongoDatabase dataBase = openConnectionToMongo(uri);
        Document dataOfDoctor = new Document().append("id", doctor.getId()).append("name", doctor.getName()).append("specialty", doctor.getSpecialty()).append("schedule ", doctor.getSchedule()).append("education", doctor.getEducation()).append("email", doctor.getEmail()).append("cellphone", doctor.getCellphone());

        String collection = "Doctor";
        MongoCollection<Document> mongoCollection = accessToCollections(dataBase, collection);
        insertOneData(dataOfDoctor, mongoCollection);
        return false;
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

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private static final String URI = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
    private static final String DATABASE_NAME = "Medical_Appointment";

    public static List<Doctor> getDoctors() {
        String collectionName = "Doctor";
        List<Doctor> doctors = new ArrayList<>();
        MongoDatabase database = getDatabase();
        MongoCollection<Document> collection = database.getCollection(collectionName);

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Doctor doctor = new Doctor(
                        doc.getString("id"),
                        doc.getString("email"),
                        doc.getString("cellphone"),
                        doc.getString("education"),
                        doc.getString("name"),
                        doc.getString("schedule"),
                        doc.getString("specialty")
                );
                doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public static boolean deleteDoctorById(String id) {
        String collectionName = "Doctor";
        MongoDatabase database = getDatabase();
        MongoCollection<Document> collection = database.getCollection(collectionName);

        try {
            Document filter = new Document("id", id);
            DeleteResult result = collection.deleteOne(filter);
            return result.getDeletedCount() > 0;
        } catch (MongoException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateDoctor(Doctor doctor) {
        String collectionName = "Doctor";
        MongoDatabase database = getDatabase();
        MongoCollection<Document> collection = database.getCollection(collectionName);

        try {
            Document filter = new Document("id", doctor.getId());
            Document update = new Document("$set", new Document("name", doctor.getName())
                    .append("specialty", doctor.getSpecialty())
                    .append("cellphone", doctor.getCellphone())
                    .append("email", doctor.getEmail())
                    .append("schedule", doctor.getSchedule()));

            UpdateResult result = collection.updateOne(filter, update);
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Doctor getDoctorById(String id) {
        String collectionName = "Doctor";
        MongoDatabase database = getDatabase();
        MongoCollection<Document> collection = database.getCollection(collectionName);

        try {
            Document filter = new Document("id", id);
            Document doc = collection.find(filter).first();

            if (doc != null) {
                return new Doctor(
                        doc.getString("id"),
                        doc.getString("name"),
                        doc.getString("specialty"),
                        doc.getString("cellphone"),
                        doc.getString("email"),
                        doc.getString("schedule")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
        String databaseName = "Medical_Appointment";
        String collectionName = "Doctor";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Recuperar documentos de la colección
            FindIterable<Document> doctorsIterable = collection.find();
            System.out.println("Número de doctores recuperados: " + doctorsIterable.spliterator().estimateSize());

            for (Document doc : doctorsIterable) {
                try {
                    Doctor doctor = new Doctor();
                    doctor.setId(doc.getString("id"));
                    doctor.setName(doc.getString("name"));
                    doctor.setSpecialty(doc.getString("specialty"));
                    doctor.setSchedule(doc.getString("schedule "));

                    // Contar citas asociadas al doctor por nombre
                    String doctorName = doctor.getName();
                    int appointmentsCount = countAppointmentsForDoctorByName(doctorName);
                    doctor.setAppointmentsCount(appointmentsCount);

                    doctors.add(doctor);
                } catch (Exception e) {
                    System.err.println("Error al convertir el documento: " + doc.toJson());
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.err.println("Error inesperado al cargar los doctores.");
            e.printStackTrace();
        }
        return doctors;
    }

    private static int countAppointmentsForDoctorByName(String doctorName) {
        int count = 0;
        String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
        String databaseName = "Medical_Appointment";
        String collectionName = "Appointment";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Contar documentos en la colección que coincidan con el nombre del doctor
            Document filter = new Document("doctor.name", doctorName);
            count = (int) collection.countDocuments(filter);
        } catch (Exception e) {
            System.err.println("Error inesperado al contar citas para el doctor con nombre " + doctorName);
            e.printStackTrace();
        }
        return count;
    }

    public static Doctor getDoctorByName(String name) {
        // Implementa la lógica para buscar el doctor por nombre
        return null;
        // Implementa la lógica para buscar el doctor por nombre
    }
}
