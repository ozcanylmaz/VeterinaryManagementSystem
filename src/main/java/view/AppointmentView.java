package view;

import entity.Appointment;
import entity.Animal;
import entity.Vet;
import service.AppointmentService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AppointmentView {

    private final AppointmentService appointmentService;
    private final Scanner scanner;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AppointmentView() {
        this.appointmentService = new AppointmentService();
        this.scanner = new Scanner(System.in);
    }

    public void startMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addAppointment();
                    break;
                case 2:
                    listAllAppointments();
                    break;
                case 3:
                    filterAppointmentsByDateAndAnimal();
                    break;
                case 0:
                    running = false;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Appointment Management Menu ---");
        System.out.println("1. Add New Appointment");
        System.out.println("2. List All Appointments");
        System.out.println("3. Filter by Date Range and Animal"); // Yeni filtre
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return -1;
        }
    }

    private LocalDateTime getLocalDateTimeInput(String prompt) throws DateTimeParseException {
        System.out.print(prompt);
        String dateTimeStr = scanner.nextLine();
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    private void addAppointment() {
        Appointment newAppointment = new Appointment();
        System.out.println("\n--- Add New Appointment ---");

        System.out.print("Enter Animal ID: ");
        int animalId = getUserChoice();

        System.out.print("Enter Vet ID: ");
        int vetId = getUserChoice();

        try {
            LocalDateTime dateTime = getLocalDateTimeInput("Enter Date and Time (YYYY-MM-DD HH:MM): ");
            newAppointment.setAppointmentDate(dateTime);

            Animal tempAnimal = new Animal();
            tempAnimal.setId(animalId);
            newAppointment.setAnimal(tempAnimal);

            Vet tempVet = new Vet();
            tempVet.setId(vetId);
            newAppointment.setVet(tempVet);

            if (appointmentService.saveAppointment(newAppointment)) {
                System.out.println(" Appointment scheduled successfully!");
            } else {
                System.err.println(" Failed to schedule appointment. Check for time conflicts or missing IDs.");
            }
        } catch (DateTimeParseException e) {
            System.err.println(" Invalid date/time format. Please use YYYY-MM-DD HH:MM format.");
        }
    }

    private void listAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        System.out.println("\n--- All Scheduled Appointments ---");
        for (Appointment appointment : appointments) {
            System.out.println(appointment.toString());
        }
    }

    private void filterAppointmentsByDateAndAnimal() {
        System.out.println("\n--- Filter Appointments ---");
        System.out.print("Enter Animal ID: ");
        int animalId = getUserChoice();

        try {
            LocalDateTime startDate = getLocalDateTimeInput("Enter Start Date/Time (YYYY-MM-DD HH:MM): ");
            LocalDateTime endDate = getLocalDateTimeInput("Enter End Date/Time (YYYY-MM-DD HH:MM): ");

            List<Appointment> appointments = appointmentService.getAppointmentsByDateRangeAndAnimal(animalId, startDate, endDate);

            if (appointments.isEmpty()) {
                System.out.println("No appointments found for Animal ID " + animalId + " in the given range.");
                return;
            }
            System.out.println("\n--- Filtered Appointments ---");
            for (Appointment appointment : appointments) {
                System.out.println(appointment.toString());
            }

        } catch (DateTimeParseException e) {
            System.err.println(" Invalid date/time format. Please use YYYY-MM-DD HH:MM format.");
        }
    }
}