/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import org.bson.Document;

/**
 *
 * @author Alexis Viteri DCO-ESPE
 */
public class DataBase {

    public static void sendToDatabase(Appointment appointment) {
        String uri = "mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Medical_Appointment");
            MongoCollection<Document> collection = database.getCollection("Appointment");
            Document doc = // Usar getEmailSent()
            new Document("id", appointment.getId()).append("dateAppointment", appointment.getDateAppointment().toString()).append("timeSlot", appointment.getTimeSlot()).append("doctor", new Document("id", appointment.getDoctor().getId()).append("name", appointment.getDoctor().getName()).append("specialty", appointment.getDoctor().getSpecialty()).append("schedule", appointment.getDoctor().getSchedule())).append("patient", new Document("id", appointment.getPatient().getId()).append("name", appointment.getPatient().getName()).append("age", appointment.getPatient().getAge()).append("email", appointment.getPatient().getEmail())).append("emailSent", appointment.getEmailSent()) // Usar getEmailSent()
            .append("hourToAppointment", appointment.getHourToAppointment() != null ? appointment.getHourToAppointment() : null);
            collection.insertOne(doc);
            System.out.println("Appointment inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
