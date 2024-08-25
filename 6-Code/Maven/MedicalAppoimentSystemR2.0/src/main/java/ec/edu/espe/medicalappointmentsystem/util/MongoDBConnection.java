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
