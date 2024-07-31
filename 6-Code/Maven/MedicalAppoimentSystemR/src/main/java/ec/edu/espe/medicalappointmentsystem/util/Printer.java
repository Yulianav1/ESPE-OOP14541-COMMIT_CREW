/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;
//import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Patient;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

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

        String pdfPath = "appointment_" + patient.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".pdf";
        try {
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Agregar título
            document.add(new Paragraph("Cita Médica")
                .setFontSize(18)
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()
            );

            // Crear tabla
            float[] columnWidths = {1, 2};
            Table table = new Table(columnWidths);

            // Encabezados
            table.addCell(new Cell().add(new Paragraph("Campo").setBold()));
            table.addCell(new Cell().add(new Paragraph("Valor").setBold()));

            // Crear un SimpleDateFormat para formatear la fecha
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            // Datos de la cita
            table.addCell(new Paragraph("Fecha:"));
            String formattedDate = appointment.getDateAppointment() != null ? dateFormat.format(appointment.getDateAppointment()) : "No disponible";
            table.addCell(new Paragraph(formattedDate));

            table.addCell(new Paragraph("Paciente:"));
            String patientName = patient.getName() != null ? patient.getName() : "No disponible";
            table.addCell(new Paragraph(patientName));

            table.addCell(new Paragraph("Cédula:"));
            String patientId = patient.getId() != null ? patient.getId() : "No disponible";
            table.addCell(new Paragraph(patientId));

            table.addCell(new Paragraph("Doctor:"));
            String doctorName = appointment.getDoctor() != null ? appointment.getDoctor().getName() : "No disponible";
            table.addCell(new Paragraph(doctorName));

            table.addCell(new Paragraph("Hora:"));
            String timeSlot = appointment.getTimeSlot() != null ? appointment.getTimeSlot() : "No disponible";
            table.addCell(new Paragraph(timeSlot));  // Cambiado de doctorSchedule a timeSlot

            // Agregar tabla al documento
            document.add(table);

            document.close();
            System.out.println("PDF generado en " + pdfPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}