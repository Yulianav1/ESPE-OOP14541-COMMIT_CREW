package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import java.util.List;
import java.util.Scanner;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class AppointmentController {
    
     public static boolean create(Appointment appointment) {
        String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";

        try {
            MongoDatabase dataBase = openConnectionToMongo(uri);
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
            MongoCollection<Document> mongoCollection = accessToCollections(dataBase, collection);
            insertOneData(dataOfAppointment, mongoCollection);
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

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static int determinateSlot(String range) {
        int slot = 0;
        String range1 = "7:00 am - 8:30 am";
        String range2 = "8:30 am - 11:00 am";
        String range3 = "11:00 am - 12:30 pm";
        String range4 = "12:30 pm - 1:00 pm";
        String range5 = "1:00 pm - 2:30 pm";
        if (range.equals(range1)) {
            slot = 1;
        } else if (range.equals(range2)) {
            slot = 2;
        } else if (range.equals(range3)) {
            slot = 3;
        } else if (range.equals(range4)) {
            slot = 4;
        } else if (range.equals(range5)) {
            slot = 5;
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
            LocalDate formattedDate = LocalDate.parse(appointmentDate.toString());
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
}
