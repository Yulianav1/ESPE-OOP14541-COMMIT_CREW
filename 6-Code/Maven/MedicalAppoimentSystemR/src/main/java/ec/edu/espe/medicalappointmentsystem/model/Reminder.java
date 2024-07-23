/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.client.FindIterable;
import java.text.SimpleDateFormat;
import java.util.Date;
import ec.edu.espe.medicalappointmentsystem.util.EmailConfig;
import ec.edu.espe.medicalappointmentsystem.util.EmailSender;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
import ec.edu.espe.medicalappointmentsystem.controller.AppointmentController;
import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import static ec.edu.espe.medicalappointmentsystem.controller.AppointmentController.loadAppointments;
import java.util.ArrayList;
import org.bson.types.ObjectId;

/**
 * Reminder Model
 * 
 * Author: Luis Vaca, TheJavaBandits, DCCO-ESPE
 */
public class Reminder {
    private Date appointmentDate;
    private Date appointmentTime;
    private String recipient;

    public Reminder(Date appointmentDate, Date appointmentTime, String recipient) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.recipient = recipient;
    }

    public static void putReminder() {
    try {
        List<Appointment> appointments = loadAppointments();

        EmailSender emailSender = new EmailSender(new EmailConfig("smtp.gmail.com", 587, "alexisviterigithub@gmail.com", "djdkbbjlijjeghcv"));

        for (Appointment appointment : appointments) {
            Patient patient = appointment.getPatient();
            if (checkDays(appointment.getDateAppointment()) && checkShipment(appointment.getEmailSent())) {
                Doctor doctor = appointment.getDoctor();
                String to = patient.getEmail();
                appointment.setHourToAppointment(DateValidator.getAppointmentTime(appointment.getTimeSlot()));
                String subject = "Recordatorio de Cita Médica";
                String body = "Estimado(a) " + patient.getName() + ",\n\n" +
                        "Este es un recordatorio de su cita médica.\n\n" +
                        "Detalles de la cita:\n" +
                        "ID de la cita: " + appointment.getIdApp() + "\n" +
                        "Fecha: " + appointment.getDateAppointment() + "\n" +
                        "Hora: " + appointment.getHourToAppointment() + "\n" +
                        "Doctor: " + doctor.getName() + "\n" +
                        "Especialidad: " + doctor.getSpecialty() + "\n\n" +
                        "Por favor, asegúrese de llegar a tiempo.\n\n" +
                        "Saludos cordiales,\n" +
                        "Su equipo médico";
                emailSender.sendMail(to, subject, body);
                System.out.println("Correo enviado a: " + to);
                
                // Verifica que appointmentId no sea null
                if (appointment.getIdApp() == null || appointment.getIdApp().isEmpty()) {
                    System.err.println("ID de cita inválido: " + appointment.getIdApp());
                    continue;
                }

                appointment.setEmailSent(true);
                updateEmailSentStatus(appointment.getIdApp(), true);
            } else {
                System.out.print(".");
            }
        }
    } catch (Exception e) {
        System.err.println("Error inesperado al procesar las citas.");
        e.printStackTrace();
    }
}

    public static boolean isValidObjectId(String id) {
    try {
        new ObjectId(id);
        return true;
    } catch (IllegalArgumentException e) {
        return false;
    }
}

    
    public static void updateEmailSentStatus(String appointmentId, boolean emailSent) {
    if (!isValidObjectId(appointmentId)) {
        System.err.println("ID de cita inválido: " + appointmentId);
        return;
    }

    String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
    String databaseName = "Medical_Appointment";
    String collectionName = "Appointment";

    try (MongoClient mongoClient = MongoClients.create(uri)) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.updateOne(eq("_id", new ObjectId(appointmentId)), new Document("$set", new Document("emailSent", emailSent)));
    } catch (Exception e) {
        System.err.println("Error inesperado al actualizar el estado de emailSent.");
        e.printStackTrace();
    }
}



    public static boolean checkDays(LocalDate appointmentDate) {
        LocalDate today = LocalDate.now();
        long daysUntilAppointment = ChronoUnit.DAYS.between(today, appointmentDate);
        return daysUntilAppointment <= 3 && daysUntilAppointment >= 0;
    }

    public static boolean checkShipment(boolean emailSent) {
        return !emailSent;
    }

    // Getters and Setters
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    // Operations
    public void sendReminder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("Sending reminder to " + recipient +
                " for appointment at " + sdf.format(appointmentDate) + " " + sdf.format(appointmentTime));
    }

    public void confirmAppointment() {
        System.out.println("Appointment confirmed for " + recipient +
                " on " + new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate) +
                " at " + new SimpleDateFormat("HH:mm").format(appointmentTime));
    }

    public void updateReminderDetails(Date newDate, Date newTime) {
        setAppointmentDate(newDate);
        setAppointmentTime(newTime);
        System.out.println("Reminder details updated for " + recipient);
    }
}