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
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AppointmentController {
    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate();
    }
    public static void sendToDatabase(Appointment appointment) {
        String uri = "mongodb+srv://alexisviterigithub:ajviteri2@ajviteri2.y5pwei7.mongodb.net/";
        
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("oop");
            MongoCollection<Document> collection = database.getCollection("student");

            Document doc = new Document("id", appointment.getId())
                    .append("dateAppointment", appointment.getDateAppointment().toString())
                    .append("timeSlot", appointment.getTimeSlot())
                    .append("doctor", new Document("id", appointment.getDoctor().getId())
                            .append("name", appointment.getDoctor().getName())
                            .append("specialty", appointment.getDoctor().getSpecialty())
                            .append("schedule", appointment.getDoctor().getSchedule()))
                    .append("patient", new Document("id", appointment.getPatient().getId())
                            .append("name", appointment.getPatient().getName())
                            .append("age", appointment.getPatient().getAge())
                            .append("email", appointment.getPatient().getEmail()))
                    .append("emailSent", appointment.getEmailSent()) // Usar getEmailSent()
                    .append("hourToAppointment", appointment.getHourToAppointment() != null ? appointment.getHourToAppointment() : null);

            collection.insertOne(doc);
            System.out.println("Appointment inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int determinateSlot(String range){
        int slot=0;
        String range1="7:00 am - 8:30 am";
        String range2="8:30 am - 11:00 am";
        String range3="11:00 am - 12:30 pm";
        String range4="12:30 pm - 1:00 pm";
        String range5="1:00 pm - 2:30 pm";
        if (range.equals(range1)){
            slot=1;
        }else if(range.equals(range2)){
            slot=2;
        }else if(range.equals(range3)){
            slot=3;
        }else if(range.equals(range4)){
            slot=4;
        }else if(range.equals(range5)){
            slot=5;
        }
        return slot;
    }

    public static Appointment addAppointment(List<Doctor> doctors, List<Patient> patients, Scanner input) {
        LocalDate appointmentDate = DateValidator.getValidAppointmentDate();
        int timeSlot = DateValidator.getValidAppointmentTime();
        Doctor selectedDoctor = inputDoctorData(doctors, input);
        Patient patient = Patient.inputPatientData(input);

        if (patient != null) {
            LocalDate formattedDate = LocalDate.parse(appointmentDate.toString());
            Appointment appointment = new Appointment(formattedDate, timeSlot, selectedDoctor, patient);
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
                System.out.println("Appointment ID: " + apt.getId());
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
            case 1: return "7:00 am - 8:30 am";
            case 2: return "8:30 am - 10:00 am";
            case 3: return "10:00 am - 11:30 am";
            case 4: return "11:30 am - 1:00 pm";
            case 5: return "1:00 pm - 2:30 pm";
            default: return "Hora no válida";
        }
    }
}
