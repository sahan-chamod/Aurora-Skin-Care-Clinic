public class Appointment {
    private String appointmentId;
    private String day;
    private String time;
    private Patient patient;
    private Dermatologist dermatologist;
    private Treatment treatment;

    public Appointment(String appointmentId, String day, String time, Patient patient, Dermatologist dermatologist, Treatment treatment) {
        this.appointmentId = appointmentId;
        this.day = day;
        this.time = time;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.treatment = treatment;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getDetails() {
        return String.format("Appointment ID: %s\nDay: %s\nTime: %s\nPatient: %s\nDermatologist: %s\nTreatment: %s\nCost: LKR %.2f",
                appointmentId, day, time, patient.getName(), dermatologist.getName(), treatment.getName(), treatment.calculateTotalWithTax());
    }
}
