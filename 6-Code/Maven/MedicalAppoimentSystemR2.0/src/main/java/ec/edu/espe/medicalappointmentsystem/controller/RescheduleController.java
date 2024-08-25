/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.Date;
import javax.swing.JOptionPane;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import java.text.ParseException;
import ec.edu.espe.medicalappointmentsystem.controller.AppointmentController;
import ec.edu.espe.medicalappointmentsystem.controller.DoctorController;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
/**
 *
 * @author Alexis Viteri DCO-ESPE
 */
public class RescheduleController {
    public boolean rescheduleAppointment(Appointment selectedAppointment, Date newDate, String newTime, String newDoctor, String newSpecialty) {
       if (selectedAppointment == null || newDate == null || newTime == null || newDoctor == null) {
            return false;
        }

         try (MongoClient mongoClient = MongoClients.create("mongodb+srv://valencia:valencia@cluster0.wmq4g6d.mongodb.net/")) {
        MongoDatabase database = mongoClient.getDatabase("Medical_Appointment");
        MongoCollection<Document> appointmentsCollection = database.getCollection("Appointment");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date time = sdf.parse(newTime);
        String formattedTime = sdf.format(time);
        Bson filter = Filters.eq("idApp", selectedAppointment.getIdApp());
        Bson updates = Updates.combine(
                Updates.set("dateAppointment", newDate),
                Updates.set("timeSlot", formattedTime),
                Updates.set("doctor.name", newDoctor)
                );
            appointmentsCollection.updateOne(filter, updates);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
public static void updateDoctorComboBox(String specialty, javax.swing.JComboBox<String> jComboBoxDoctor, javax.swing.JComboBox<String> jComboBoxSchedule) {
    List<Doctor> doctors = DoctorController.loadDoctors();
    DefaultComboBoxModel<String> doctorModel = new DefaultComboBoxModel<>();

    for (Doctor doctor : doctors) {
        if (doctor.getSpecialty().equals(specialty)) {
            doctorModel.addElement(doctor.getName());
        }
    }

    if (doctorModel.getSize() == 0) {
        doctorModel.addElement("No hay doctores disponibles");
    }

    jComboBoxDoctor.setModel(doctorModel);

    RescheduleController rescheduleController = new RescheduleController();
    rescheduleController.updateScheduleForDoctor(null, jComboBoxSchedule);
}


    public void updateScheduleForDoctor(String doctorName, javax.swing.JComboBox<String> jComboBoxSchedule) {
    List<Doctor> doctors = DoctorController.loadDoctors();
    DefaultComboBoxModel<String> timeModel = new DefaultComboBoxModel<>();

    // Buscar el doctor por nombre
    for (Doctor doctor : doctors) {
        if (doctor.getName().equals(doctorName)) {
            String schedule = doctor.getSchedule(); 
            if (schedule != null) {
                addHourlySlots(schedule, timeModel); 
            }
            break;
        }
    }

  
    if (timeModel.getSize() == 0) {
        timeModel.addElement("Horario no disponible");
    }

    
    jComboBoxSchedule.setModel(timeModel);
}


private void addHourlySlots(String schedule, DefaultComboBoxModel<String> timeModel) {
    if (schedule.contains("completo")&&schedule.contains("7:00")) {
        
        addTimeSlots("7:00", "15:00", timeModel); 
    } 
    else if (schedule.contains("completo")&&schedule.contains("13:00")) {
        
        addTimeSlots("13:00", "21:00", timeModel);
        } else if (schedule.contains("parcial")) {
        
        addTimeSlots("7:00", "15:00", timeModel); 
    } else if (schedule.matches("\\d{2}:\\d{2}")) {
        
        String startHour = schedule.trim();
        LocalTime start = LocalTime.parse(startHour, DateTimeFormatter.ofPattern("H:mm"));
        LocalTime end;
        if (start.isBefore(LocalTime.of(12, 0))) {
            end = start.plusHours(8); 
        } else {
            end = start.plusHours(8); 
        }
        addTimeSlots(start.format(DateTimeFormatter.ofPattern("H:mm")), end.format(DateTimeFormatter.ofPattern("H:mm")), timeModel);
    } else {

        timeModel.addElement("Horario no disponible");
    }
}

private void addTimeSlots(String startHour, String endHour, DefaultComboBoxModel<String> timeModel) {
    LocalTime start = LocalTime.parse(startHour, DateTimeFormatter.ofPattern("H:mm"));
    LocalTime end = LocalTime.parse(endHour, DateTimeFormatter.ofPattern("H:mm"));
    
    while (start.isBefore(end)) {
        LocalTime nextHour = start.plusHours(1);
        if (nextHour.isAfter(end)) {
            nextHour = end; 
        }
        timeModel.addElement(start.format(DateTimeFormatter.ofPattern("h:mm a")) + " - " + nextHour.format(DateTimeFormatter.ofPattern("h:mm a")));
        start = nextHour;
    }
}

}