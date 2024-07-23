/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Patient;
import java.io.FileNotFoundException;

public class Printer {
    
    public static void printAppointment(Appointment appointment) {
        if (appointment == null) {
            System.out.println("La cita es nula.");
            return;
        }
        
        Patient patient = appointment.getPatient();
        if (patient == null) {
            System.out.println("El paciente de la cita es nulo.");
            return;
        }
        
        String pdfPath = "appointment.pdf";
        try {
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            document.add(new Paragraph("Cita Médica"));
            document.add(new Paragraph("Fecha: " + appointment.getDateAppointment().toString()));
            document.add(new Paragraph("Paciente: " + patient.getName()));
            document.add(new Paragraph("Cedula: " + patient.getId()));
            document.add(new Paragraph("Doctor: " + appointment.getDoctor().getName()));
            document.add(new Paragraph("Hora: " + appointment.getDoctor().getSchedule()));
            
            document.close();
            System.out.println("PDF generado en " + pdfPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}