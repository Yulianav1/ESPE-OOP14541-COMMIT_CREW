/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ec.edu.espe.medicalappointmentsystem.model.Appointment;
import ec.edu.espe.medicalappointmentsystem.model.Doctor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final ObjectMapper mapper;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static void saveAppointments(List<Appointment> appointments) {
        try {
            mapper.writeValue(new File("appointments.json"), appointments);
            System.out.println("Citas guardadas correctamente en un archivo JSON.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static List<Appointment> loadAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        File file = new File("appointments.json");
        if (file.exists() && file.length() > 0) {
            try {
                appointments = mapper.readValue(file, new TypeReference<List<Appointment>>() {
                });
            } catch (IOException e) {
                System.err.println("Error al cargar citas desde el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Archivo no encontrado, iniciando con una lista vacia.");
        }
        return appointments;
    }

    public static void addAndSaveAppointment(Appointment appointment) {
        List<Appointment> appointments = loadAppointments();
        appointments.add(appointment);
        saveAppointments(appointments);
    }

    public static void markEmailSent(Appointment appointment) {
        List<Appointment> appointments = loadAppointments();
        for (Appointment app : appointments) {
            if (app.getId().equals(appointment.getId())) {
                app.setEmailSent(true); // Marcar como enviado
                break;
            }
        }
        saveAppointments(appointments);
    }

    public static void saveDoctorsToFile(List<Doctor> doctorList, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(doctorList, writer);
            System.out.println("Lista de doctores guardada en el archivo: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al guardar la lista de doctores en el archivo: " + e.getMessage());
        }
    }
}
