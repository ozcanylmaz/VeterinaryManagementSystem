package view;

import entity.Vaccine;
import entity.Animal;
import service.VaccineService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VaccineView {

    private final VaccineService vaccineService;
    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public VaccineView() {
        this.vaccineService = new VaccineService();
        this.scanner = new Scanner(System.in);
    }

    public void startMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addVaccine();
                    break;
                case 2:
                    listAllVaccines();
                    break;
                case 3:
                    listVaccinesByAnimal();
                    break;
                case 4:
                    filterByProtectionEndDate();
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
        System.out.println("\n--- Vaccine Management Menu ---");
        System.out.println("1. Add New Vaccine Record");
        System.out.println("2. List All Vaccines");
        System.out.println("3. List Vaccines by Animal ID (Check History)");
        System.out.println("4. Filter by Protection End Date"); // Yeni filtre
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

    private LocalDate getLocalDateInput(String prompt) throws DateTimeParseException {
        System.out.print(prompt);
        String dateStr = scanner.nextLine();
        return LocalDate.parse(dateStr, dateFormatter);
    }

    private void addVaccine() {
        // ... (addVaccine metodu buraya gelecek) ...
    }

    private void listAllVaccines() {
        // ... (listAllVaccines metodu buraya gelecek) ...
    }

    private void listVaccinesByAnimal() {
        // ... (listVaccinesByAnimal metodu buraya gelecek) ...
    }

    private void filterByProtectionEndDate() {
        System.out.println("\n--- Filter Vaccines by End Date ---");

        try {
            LocalDate startDate = getLocalDateInput("Enter Start Date (YYYY-MM-DD): ");
            LocalDate endDate = getLocalDateInput("Enter End Date (YYYY-MM-DD): ");

            List<Vaccine> vaccines = vaccineService.getVaccinesByProtectionEndDateRange(startDate, endDate);

            if (vaccines.isEmpty()) {
                System.out.println("No vaccine records found in the specified end date range.");
                return;
            }
            System.out.println("\n--- Filtered Vaccine Records ---");
            for (Vaccine vaccine : vaccines) {
                System.out.println(vaccine.toString());
            }

        } catch (DateTimeParseException e) {
            System.err.println(" Invalid date format. Please use YYYY-MM-DD format.");
        }
    }
}