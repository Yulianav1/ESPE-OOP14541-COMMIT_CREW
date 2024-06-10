package ec.edu.espe.medicalappointmentsystem.controller;

import ec.edu.espe.medicalappointmentsystem.model.Doctor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CommitCrew
 */
public class DoctorController {
    private List<Doctor> doctorList;
    
    public DoctorController() {
        this.doctorList = new ArrayList<>();
    }
    
    public void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
    }
    
}
