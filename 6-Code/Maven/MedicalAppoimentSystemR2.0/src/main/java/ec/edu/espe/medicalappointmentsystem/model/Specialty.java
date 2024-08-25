
package ec.edu.espe.medicalappointmentsystem.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CommitCrew
 */
public class Specialty {
    
    private String specialtyName;
    private String description;
    private ScheduleManager scheduleManager;

    public Specialty(String specialtyName, String description) {
        this.specialtyName = specialtyName;
        this.description = description;
        this.scheduleManager = new ScheduleManager();
    }

    @Override
    public String toString() {
        return "Specialty{" + "specialtyName=" + specialtyName + ", description=" + description + '}';
    }
    
    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Método para acceder a los horarios disponibles desde Specialty
    public String getAvailableSchedule(int option) {
        return scheduleManager.getAvailableSchedule(option);
    }

    // Clase interna para manejar los horarios
    private class ScheduleManager {

        private final Map<Integer, String> schedules;

        public ScheduleManager() {
            schedules = new HashMap<>();
            schedules.put(1, "7:00 am a 8:30 am");
            schedules.put(2, "8:30 am a 10:00 am");
            schedules.put(3, "10:00 am a 11:30 am");
            schedules.put(4, "11:30 am a 1:00 pm");
            schedules.put(5, "1:00 pm a 2:30 pm");
        }

        public String getAvailableSchedule(int option) {
            return schedules.getOrDefault(option, "Horario no disponible");
        }
    }
}