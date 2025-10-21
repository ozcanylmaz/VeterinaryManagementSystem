package view;

import entity.Owner;
import service.OwnerService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OwnerView {

    private final OwnerService ownerService;
    private final Scanner scanner;

    public OwnerView() {
        this.ownerService = new OwnerService();
        this.scanner = new Scanner(System.in);
    }

    public void startMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addOwner();
                    break;
                case 2:
                    listAllOwners();
                    break;
                case 3:
                    updateOwner();
                    break;
                case 4:
                    deleteOwner();
                    break;
                case 5:
                    filterOwnersByName();
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
        System.out.println("\n--- Owner Management Menu ---");
        System.out.println("1. Add New Owner");
        System.out.println("2. List All Owners");
        System.out.println("3. Update Owner");
        System.out.println("4. Delete Owner");
        System.out.println("5. Filter Owners by Name"); // <-- Yeni Menü Başlığı
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

    private void addOwner() {
        Owner newOwner = new Owner();
        System.out.println("\n--- Add New Owner ---");
        System.out.print("Name: ");
        newOwner.setName(scanner.nextLine());

        System.out.print("Phone: ");
        newOwner.setPhone(scanner.nextLine());

        System.out.print("Email: ");
        newOwner.setMail(scanner.nextLine());

        System.out.print("Address: ");
        newOwner.setAddress(scanner.nextLine());

        System.out.print("City: ");
        newOwner.setCity(scanner.nextLine());

        if (ownerService.saveOwner(newOwner)) {
            System.out.println(" Owner saved successfully!");
        } else {
            System.err.println(" Failed to save owner. Check input data.");
        }
    }

    private void listAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }
        System.out.println("\n--- All Registered Owners ---");
        for (Owner owner : owners) {
            System.out.println(owner.toString());
        }
    }

    private void updateOwner() {
        System.out.print("\nEnter ID of Owner to update: ");
        int idToUpdate = getUserChoice();

        Owner existingOwner = ownerService.getOwner(idToUpdate);
        if (existingOwner == null) {
            System.err.println("Owner not found with ID: " + idToUpdate);
            return;
        }

        System.out.println("--- Updating Owner ID: " + idToUpdate + " ---");
        System.out.println("Current Name: " + existingOwner.getName());
        System.out.print("Enter NEW Name (or press Enter to skip): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            existingOwner.setName(newName);
        }

        System.out.println("Current Phone: " + existingOwner.getPhone());
        System.out.print("Enter NEW Phone (or press Enter to skip): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.trim().isEmpty()) {
            existingOwner.setPhone(newPhone);
        }
        // ... Diğer alanlar

        if (ownerService.updateOwner(existingOwner)) {
            System.out.println(" Owner updated successfully!");
        } else {
            System.err.println(" Failed to update owner. Check service validation.");
        }
    }

    private void deleteOwner() {
        System.out.print("\nEnter ID of Owner to delete: ");
        int idToDelete = getUserChoice();

        if (ownerService.deleteOwner(idToDelete)) {
            System.out.println(" Owner deleted successfully!");
        } else {
            System.err.println(" Failed to delete owner. Check if ID exists.");
        }
    }

    private void filterOwnersByName() {
        System.out.print("\nEnter Name to filter: ");
        String name = scanner.nextLine();

        List<Owner> owners = ownerService.getOwnersByName(name);
        if (owners.isEmpty()) {
            System.out.println("No owners found matching the name '" + name + "'.");
            return;
        }
        System.out.println("\n--- Filtered Owners ---");
        for (Owner owner : owners) {
            System.out.println(owner.toString());
        }
    }
}