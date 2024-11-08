public class Invoice {
    private Patient patient;
    private Treatment treatment;
    private double registrationFee;
    private double treatmentFee;
    private double totalCost;

    public Invoice(Patient patient, Treatment treatment, double registrationFee, double treatmentFee, double totalCost) {
        this.patient = patient;
        this.treatment = treatment;
        this.registrationFee = registrationFee;
        this.treatmentFee = treatmentFee;
        this.totalCost = totalCost;
    }

    public void printInvoice() {
        System.out.printf("Invoice:\nPatient: %s\nTreatment: %s\nRegistration Fee: LKR %.2f\nTreatment Fee: LKR %.2f\nTotal Cost (with Tax): LKR %.2f\n",
                patient.getName(), treatment.getName(), registrationFee, treatmentFee, totalCost);
    }

    public String getInvoiceDetails() {
        return String.format("Invoice for %s - Treatment: %s - Total: LKR %.2f", patient.getName(), treatment.getName(), totalCost);
    }
}
