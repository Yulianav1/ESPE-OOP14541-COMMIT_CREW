    package ec.edu.espe.medicalappointmentsystem.model;

    import ec.edu.espe.medicalappointmentsystem.util.DateValidator;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import java.time.LocalDate;
import java.util.Date;
    import java.util.List;
    import java.util.Scanner;
    import java.util.UUID;
@JsonIgnoreProperties(ignoreUnknown = true)
    public class Appointment {

        private String idApp;
        private Date dateAppointment;
        private String timeSlot;
        private Doctor doctor;
        private Patient patient;
        private boolean emailSent;
        private String hourToAppointment;

        public Appointment() {
        }

        public Appointment(String idApp, Date dateAppointment, String timeSlot, Doctor doctor, Patient patient) {
            this.idApp = idApp;
            this.dateAppointment = dateAppointment;
            this.timeSlot = timeSlot;
            this.doctor = doctor;
            this.patient = patient;
            this.emailSent = false; 
            this.hourToAppointment = null; 
        }

    public boolean isEmailSent() {
        return emailSent;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

        @Override
        public String toString() {
            return "Appointment{"
                    + "id='" + idApp + '\''
                    + ", dateAppointment='" + dateAppointment + '\''
                    + ", timeSlot=" + timeSlot
                    + ", doctor=" + doctor
                    + ", patient=" + patient
                    + ", emailSent=" + emailSent
                    + ", hourToAppointment=" + hourToAppointment
                    + '}';
        }

        public String getIdApp() {
            return idApp;
        }

        public Date getDateAppointment() {
            return dateAppointment;
        }

        public String getTimeSlot() {
            return timeSlot;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public Patient getPatient() {
            return patient;
        }

        public boolean getEmailSent() {
            return emailSent;
        }

        public String getHourToAppointment() {
            return hourToAppointment;
        }

        public void setEmailSent(boolean emailSent) {
            this.emailSent = emailSent;
        }

        public void setHourToAppointment(String hourToAppointment) {
            this.hourToAppointment = hourToAppointment;
        }

    }
