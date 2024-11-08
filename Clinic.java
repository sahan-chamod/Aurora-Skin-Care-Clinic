import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private List<Dermatologist> dermatologists;
    private List<Treatment> treatments;
    private List<Appointment> appointments;
    private int appointmentCounter;

    public Clinic() {
        this.dermatologists = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.appointmentCounter = 1; 

        
        dermatologists.add(new Dermatologist("Dr. Smith"));
        dermatologists.add(new Dermatologist("Dr. Jones"));

        
        treatments.add(new Treatment("Acne Treatment", 2750.00, 500.00));  
        treatments.add(new Treatment("Skin Whitening", 7650.00, 500.00));
        treatments.add(new Treatment("Mole Removal", 3850.00, 500.00));
        treatments.add(new Treatment("Laser Treatment", 12500.00, 500.00));
    }

    public List<Dermatologist> getDermatologists() {
        return dermatologists;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void makeAppointment(Patient patient, String day, String time, Dermatologist dermatologist, Treatment treatment) {
        String appointmentId = "APT" + appointmentCounter++;
        Appointment appointment = new Appointment(appointmentId, day, time, patient, dermatologist, treatment);
        appointments.add(appointment);
        System.out.println("Appointment booked successfully! Appointment ID: " + appointmentId);
    }

    public void viewAppointmentsByDay(String day) {
        boolean found = false; 
        for (Appointment appointment : appointments) {
            if (appointment.getDetails().contains(day)) {
                System.out.println(appointment.getDetails());
                found = true; 
            }
        }
        if (!found) {
            System.out.println("No appointments found for " + day + ".");
        }
    }

    public Appointment searchAppointment(String searchTerm) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(searchTerm) || 
                appointment.getDetails().contains(searchTerm)) {
                return appointment;
            }
        }
        return null; 
    }

    public List<String> getAvailableDays() {
        List<String> availableDays = new ArrayList<>();
        availableDays.add("Monday");
        availableDays.add("Wednesday");
        availableDays.add("Friday");
        availableDays.add("Saturday");
        return availableDays;
    }
}
