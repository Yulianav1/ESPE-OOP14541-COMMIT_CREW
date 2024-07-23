package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static com.mongodb.client.model.Sorts.descending;

public class AppointmentController {
    
    public static boolean create(Appointment appointment) {
        String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";

        try {
            MongoDatabase database = openConnectionToMongo(uri);
            Document dataOfAppointment = new Document("idApp", appointment.getIdApp())
                    .append("dateAppointment", appointment.getDateAppointment().toString())
                    .append("timeSlot", appointment.getTimeSlot())
                    .append("doctor", new Document("name", appointment.getDoctor().getName())
                                         .append("specialty", appointment.getDoctor().getSpecialty()))
                    .append("patient", new Document("id", appointment.getPatient().getId())
                                         .append("name", appointment.getPatient().getName())
                                         .append("age", appointment.getPatient().getAge())
                                         .append("email", appointment.getPatient().getEmail())
                                         .append("cellphone", appointment.getPatient().getCellphone()))
                    .append("emailSent", appointment.getEmailSent())
                    .append("hourToAppointment", appointment.getHourToAppointment());

            String collection = "Appointment";
            MongoCollection<Document> mongoCollection = accessToCollections(database, collection);
            insertOneData(dataOfAppointment, mongoCollection);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
       
    public static MongoDatabase openConnectionToMongo(String uri) {
        MongoClient mongoClient = MongoClients.create(uri);
        return mongoClient.getDatabase("Medical_Appointment");
    }

    public static MongoCollection<Document> accessToCollections(MongoDatabase database, String collection) {
        return database.getCollection(collection);
    }

    public static void insertOneData(Document data, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertOne(data);
    }

    public static void insertMoreThanOneData(List<Document> listOfData, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertMany(listOfData);
    }

    public static void getAllCollection(MongoCollection<Document> mongoCollection) {
        Document findDocument = new Document(); // Obtiene todos los documentos

        MongoCursor<Document> resultDocument = mongoCollection.find(findDocument).iterator();

        System.out.println("***************************************");
        System.out.println("All appointments:");
        System.out.println("***************************************");
        while (resultDocument.hasNext()) {
            System.out.println(resultDocument.next().toJson());
        }
    }

    public static void editDocuments(String key, String data, String newData, MongoCollection<Document> mongoCollection) {
        Document findDocument = new Document(key, data);
        Document updateDocument = new Document("$set", new Document(key, newData));
        mongoCollection.findOneAndUpdate(findDocument, updateDocument);
    }

    public static void deleteDocuments(String key, String data, MongoCollection<Document> mongoCollection) {
        Document findDocument = new Document(key, data);
        mongoCollection.findOneAndDelete(findDocument);
    }

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static int determinateSlot(String range) {
        int slot = 0;
        switch (range) {
            case "7:00 am - 8:30 am":
                slot = 1;
                break;
            case "8:30 am - 10:00 am":
                slot = 2;
                break;
            case "10:00 am - 11:30 am":
                slot = 3;
                break;
            case "11:30 am - 1:00 pm":
                slot = 4;
                break;
            case "1:00 pm - 2:30 pm":
                slot = 5;
                break;
            default:
                slot = 0; // Hora no válida
                break;
        }
        return slot;
    }
    
    public static Appointment addAppointment(List<Doctor> doctors, List<Patient> patients, Scanner input) {
        LocalDate appointmentDate = DateValidator.getValidAppointmentDate();
        int timeSlot = DateValidator.getValidAppointmentTime();
        Doctor selectedDoctor = inputDoctorData(doctors, input);
        Patient patient = Patient.inputPatientData(input);
        String idApp = UUID.randomUUID().toString();

        if (patient != null) {
            Appointment appointment = new Appointment(idApp, appointmentDate, timeSlot, selectedDoctor, patient);
            FileManager.addAndSaveAppointment(appointment);
            System.out.println("Cita creada exitosamente.");
            return appointment;
        } else {
            System.out.println("No se pudo crear la cita. Los datos del paciente no eran válidos.");
            return null;
        }
    }
    
    public static void viewAppointments() {
        List<Appointment> appointments = FileManager.loadAppointments();
        System.out.println("Viendo las citas:");
        if (appointments.isEmpty()) {
            System.out.println("Sin citas registradas.");
        } else {
            for (Appointment apt : appointments) {
                System.out.println("Appointment ID: " + apt.getIdApp());
                System.out.println("Doctor: " + apt.getDoctor().getName());
                System.out.println("Especialidad: " + apt.getDoctor().getSpecialty());
                System.out.println("Fecha: " + apt.getDateAppointment());
                System.out.println("Hora: " + getTimeSlotString(apt.getTimeSlot()));
                System.out.println("Paciente: " + apt.getPatient().getName());
                System.out.println("-------------------");
            }
        }
    }

    private static Doctor inputDoctorData(List<Doctor> doctors, Scanner input) {
        System.out.println("Seleccionar un doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName() + " - " + doctors.get(i).getSpecialty());
        }
        int choice;
        do {
            System.out.print("Ingrese uno de los números: ");
            while (!input.hasNextInt()) {
                System.out.println("Entrada inválida, ingrese un número correspondiente a un doctor:");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine();
        } while (choice < 1 || choice > doctors.size());

        return doctors.get(choice - 1);
    }

    private static String getTimeSlotString(int timeSlot) {
        switch (timeSlot) {
            case 1:
                return "7:00 am - 8:30 am";
            case 2:
                return "8:30 am - 10:00 am";
            case 3:
                return "10:00 am - 11:30 am";
            case 4:
                return "11:30 am - 1:00 pm";
            case 5:
                return "1:00 pm - 2:30 pm";
            default:
                return "Hora no válida";
        }
    }

    public static List<Appointment> loadAppointments() {
    List<Appointment> appointments = new ArrayList<>();
    String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
    String databaseName = "Medical_Appointment";
    String collectionName = "Appointment";

    try (MongoClient mongoClient = MongoClients.create(uri)) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        // Contar documentos en la colección
        long count = collection.countDocuments();
        System.out.println("Número de citas en la colección: " + count);

        // Recuperar documentos
        FindIterable<Document> appointmentsIterable = collection.find();
        System.out.println("Número de citas recuperadas: " + appointmentsIterable.spliterator().estimateSize());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Registra el módulo para manejar LocalDate
        objectMapper.findAndRegisterModules(); // Registra otros módulos disponibles automáticamente

        for (Document doc : appointmentsIterable) {
            try {
                Appointment appointment = objectMapper.convertValue(doc, Appointment.class);
                appointments.add(appointment);
            } catch (Exception e) {
                System.err.println("Error al convertir el documento: " + doc.toJson());
                e.printStackTrace(); // Imprimir error específico de conversión
            }
        }

    } catch (Exception e) {
        System.err.println("Error inesperado al cargar las citas.");
        e.printStackTrace();
    }
    return appointments;
}
}
