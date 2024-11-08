import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinic clinic = new Clinic();

        // Main loop for user interaction
        while (true) {
            System.out.println("Welcome to Aurora Skin Care Clinic");
            System.out.println("1. Make Appointment");
            System.out.println("3. Update Appointment");
            System.out.println("2. View Appointments by Day");
            System.out.println("3. Search Appointment");
            System.out.println("3. generate Appointment");
            System.out.println("3. ViewAll Appointment");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1: // Make Appointment
                    System.out.print("Enter NIC: ");
                    String nic = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    Patient patient = new Patient(nic, name, email, phoneNumber);
                    
                    // Choose dermatologist
                    System.out.print("Choose Dermatologist (1: Dr. Smith, 2: Dr. Jones): ");
                    int docChoice = scanner.nextInt();
                    Dermatologist dermatologist = clinic.getDermatologists().get(docChoice - 1);

                    // Choose treatment
                    System.out.println("Available Treatments:");
                    List<Treatment> treatments = clinic.getTreatments();
                    for (int i = 0; i < treatments.size(); i++) {
                        System.out.println((i + 1) + ". " + treatments.get(i).getName());
                    }
                    System.out.print("Choose Treatment: ");
                    int treatChoice = scanner.nextInt();
                    Treatment treatment = treatments.get(treatChoice - 1);

                    // Choose appointment day
                    System.out.println("Available Days:");
                    List<String> availableDays = clinic.getAvailableDays();
                    for (int i = 0; i < availableDays.size(); i++) {
                        System.out.println((i + 1) + ". " + availableDays.get(i));
                    }
                    System.out.print("Choose Appointment Day: ");
                    int dayChoice = scanner.nextInt();
                    String day = availableDays.get(dayChoice - 1);

                    // Choose appointment time
                    System.out.println("Available Times for " + day + ":");
                    List<String> availableTimes = dermatologist.getSchedule().get(day);
                    for (int i = 0; i < availableTimes.size(); i++) {
                        System.out.println((i + 1) + ". " + availableTimes.get(i));
                    }
                    System.out.print("Choose Appointment Time: ");
                    int timeChoice = scanner.nextInt();
                    String time = availableTimes.get(timeChoice - 1);

                    // Make appointment
                    clinic.makeAppointment(patient, day, time, dermatologist, treatment);

                   
                    System.out.print("");
                    System.out.print("");
                    System.out.print("invoice");

                    double totalCost = treatment.calculateTotalWithTax();
                    System.out.printf("Invoice:\nPatient: %s\nTreatment: %s\nRegistration Fee: LKR %.2f\nTreatment Fee: LKR %.2f\nTotal Cost (with Tax): LKR %.2f\n",
                            patient.getName(), treatment.getName(), treatment.getRegistrationFee(), treatment.getPrice(), totalCost);
                    break;

                case 2: // View Appointments by Day
                    System.out.print("Enter day to view appointments (e.g., Monday): ");
                    String viewDay = scanner.nextLine();
                    clinic.viewAppointmentsByDay(viewDay);
                    break;

                case 3: // Search Appointment
                    System.out.print("Enter Appointment ID or Patient Name to search: ");
                    String searchTerm = scanner.nextLine();
                    Appointment appointment = clinic.searchAppointment(searchTerm);
                    if (appointment != null) {
                        System.out.println(appointment.getDetails());
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;

                case 4: // Exit
                    System.out.println("Thank you for using the clinic system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
