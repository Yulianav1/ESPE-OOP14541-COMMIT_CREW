package ec.edu.espe.medicalappointmentsystem.util;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class MongoDBConnection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private static final String URI = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
    private static final String DATABASE_NAME = "Medical_Appointment";

    private MongoDBConnection() {
    }

    public static synchronized MongoDatabase getDatabase() {
        if (database == null) {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase(DATABASE_NAME);
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
                        doc.getString("cellphone"),
                        doc.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Appointment> getAppointmentsByDoctorId(String doctorId) {
    List<Appointment> appointments = new ArrayList<>();
    String collectionName = "Appointment";
    MongoDatabase database = getDatabase();
    MongoCollection<Document> collection = database.getCollection(collectionName);

    try {
        Document filter = new Document("doctor.id", doctorId);
        try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Conversión de los documentos a objetos Doctor y Patient
                Document doctorDoc = doc.get("doctor", Document.class);
                Doctor doctor = new Doctor(
                        doctorDoc.getString("id"),
                        doctorDoc.getString("name"),
                        doctorDoc.getString("specialty"),
                        doctorDoc.getString("cellphone"),
                        doctorDoc.getString("email"),
                        doctorDoc.getString("schedule")
                );

                Document patientDoc = doc.get("patient", Document.class);
                Patient patient = new Patient(
                        patientDoc.getString("id"),
                        patientDoc.getString("name"),
                        patientDoc.getInteger("age"),
                        patientDoc.getString("email"),
                        patientDoc.getString("cellphone")
                );

                Appointment appointment = new Appointment(
                        doc.getString("idApp"),
                        doc.getDate("dateAppointment"),
                        doc.getString("timeSlot"),
                        doctor,
                        patient
                );
                appointments.add(appointment);
            }
        }
    } catch (MongoException e) {
        e.printStackTrace();
    }
    return appointments;
}


}
