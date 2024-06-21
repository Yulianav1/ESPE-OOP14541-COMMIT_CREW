/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import ec.edu.espe.medicalappointmentsystem.util.EmailConfig;
import ec.edu.espe.medicalappointmentsystem.util.EmailSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import ec.edu.espe.medicalappointmentsystem.util.EmailConfig;
import ec.edu.espe.medicalappointmentsystem.util.EmailSender;
import java.io.FileNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import ec.edu.espe.medicalappointmentsystem.util.FileManager;
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
      public static void PutReminder() {
    try {
            // Configuración de ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Registrar el módulo para LocalDate

            // Construir la ruta al archivo appointments.json
            String filePath = "appointments.json";

            // Leer el JSON y convertirlo a lista de Appointment
            List<Appointment> appointments = objectMapper.readValue(new File(filePath),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Appointment.class));
            EmailSender emailSender = new EmailSender(new EmailConfig("smtp.gmail.com", 587, "alexisviterigithub@gmail.com", "djdkbbjlijjeghcv"));

        for (Appointment appointment : appointments) {
            Patient patient = appointment.getPatient();
            if(checkDays(appointment.getDateAppointment())&&checkShipment(appointment.getEmailSent())){
            Doctor doctor = appointment.getDoctor();
            String to = patient.getEmail();
            String subject = "Recordatorio de Cita Médica";
            String body = "Estimado(a) " + patient.getName() + ",\n\n" +
                    "Este es un recordatorio de su cita médica.\n\n" +
                    "Detalles de la cita:\n" +
                    "ID de la cita: " + appointment.getId() + "\n" +
                    "Fecha: " + appointment.getDateAppointment() + "\n" +
                    "Doctor: " + doctor.getName() + "\n" +
                    "Especialidad: " + doctor.getSpecialty() + "\n\n" +
                    "Por favor, asegúrese de llegar a tiempo.\n\n" +
                    "Saludos cordiales,\n" +
                    "Su equipo médico";

            // Enviar el correo
            emailSender.sendMail(to, subject, body);
            System.out.println("Correo enviado a: " + to);
            appointment.setEmailSent(true);
            
            }else{
                System.out.println(".......................");
            }
   
        }
         FileManager.saveAppointments(appointments);

    } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo appointments.json no encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error de E/S al intentar leer el archivo.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado al procesar las citas.");
            e.printStackTrace();
        }
    }
      public static boolean checkDays(LocalDate appointmentDate){
      LocalDate today = LocalDate.now();
        long daysUntilAppointment = ChronoUnit.DAYS.between(today, appointmentDate);
        return daysUntilAppointment <= 3 && daysUntilAppointment >= 0;
      }
      public static boolean checkShipment(boolean emailSent){
          boolean confirmer=false;
          if(emailSent == false){
              confirmer=true;
          }
        return confirmer;
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
