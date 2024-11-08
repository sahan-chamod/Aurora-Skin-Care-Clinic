import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Invoice> allInvoices = new ArrayList<>(); // To store all invoices

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinic clinic = new Clinic();

        // Main loop for user interaction
        boolean running = true;
        while (running) {
            try {
                System.out.println("Welcome to Aurora Skin Care Clinic");
                System.out.println("1. Make Appointment");
                System.out.println("2. View Appointments by Day");
                System.out.println("3. Search Appointment by ID / patient name");
                System.out.println("4. Generate Invoices");
                System.out.println("5. View All Invoices");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        makeAppointment(scanner, clinic);
                        break;

                    case 2:
                        viewAppointmentsByDay(scanner, clinic);
                        break;

                    case 3:
                        searchAppointment(scanner, clinic);
                        break;

                    case 4:
                        generateInvoices(scanner, clinic);
                        break;

                    case 5:
                        viewAllInvoices();
                        break;

                    case 6:
                        System.out.println("Thank you for using the clinic system.");
                        running = false; // Exit the loop
                        break;

                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        scanner.close(); // Close the scanner
    }

    private static void makeAppointment(Scanner scanner, Clinic clinic) {
        try {
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
            scanner.nextLine(); // Consume newline
            Dermatologist dermatologist = clinic.getDermatologists().get(docChoice - 1);

            // Choose treatment
            System.out.println("Available Treatments:");
            List<Treatment> treatments = clinic.getTreatments();
            for (int i = 0; i < treatments.size(); i++) {
                System.out.println((i + 1) + ". " + treatments.get(i).getName());
            }
            System.out.print("Choose Treatment: ");
            int treatChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Treatment treatment = treatments.get(treatChoice - 1);

            // Choose appointment day
            System.out.println("Available Days:");
            List<String> availableDays = clinic.getAvailableDays();
            for (int i = 0; i < availableDays.size(); i++) {
                System.out.println((i + 1) + ". " + availableDays.get(i));
            }
            System.out.print("Choose Appointment Day: ");
            int dayChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String day = availableDays.get(dayChoice - 1);

            // Choose appointment time
            System.out.println("Available Times for " + day + ":");
            List<String> availableTimes = dermatologist.getSchedule().get(day);
            for (int i = 0; i < availableTimes.size(); i++) {
                System.out.println((i + 1) + ". " + availableTimes.get(i));
            }
            System.out.print("Choose Appointment Time: ");
            int timeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String time = availableTimes.get(timeChoice - 1);

            // Check if the time slot is available
            if (dermatologist.isTimeSlotAvailable(day, time)) {
                // Make the appointment
                clinic.makeAppointment(patient, day, time, dermatologist, treatment);

                // Generate invoice
                double totalCost = treatment.calculateTotalWithTax();
                Invoice invoice = new Invoice(patient, treatment, treatment.getRegistrationFee(), treatment.getPrice(), totalCost);
                allInvoices.add(invoice); // Add to the list of invoices
                System.out.println("Appointment confirmed. Invoice generated.");
                invoice.printInvoice(); // Print the invoice immediately

                // Book the time slot
                dermatologist.bookTimeSlot(day, time); // Mark the time slot as booked
            } else {
                System.out.println("The selected time slot is already booked. Please choose another time.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please ensure you're entering a valid number.");
            scanner.nextLine(); // Clear the buffer
        }
    }

    private static void viewAppointmentsByDay(Scanner scanner, Clinic clinic) {
        System.out.print("Enter day to view appointments (e.g., Monday): ");
        String viewDay = scanner.nextLine();
        clinic.viewAppointmentsByDay(viewDay);
    }

    private static void searchAppointment(Scanner scanner, Clinic clinic) {
        System.out.print("Enter Appointment ID or Patient Name to search: ");
        String searchTerm = scanner.nextLine();
        Appointment appointment = clinic.searchAppointment(searchTerm);
        if (appointment != null) {
            System.out.println(appointment.getDetails());
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private static void generateInvoices(Scanner scanner, Clinic clinic) {
        System.out.println("Generating invoices...");
        // Logic to generate invoices, if any specific actions are required
    }

    private static void viewAllInvoices() {
        System.out.println("All Generated Invoices:");
        if (allInvoices.isEmpty()) {
            System.out.println("No invoices available.");
        } else {
            for (Invoice inv : allInvoices) {
                System.out.println(inv.getInvoiceDetails());
            }
        }
    }
}
