package view;

import entity.Animal;
import entity.Owner;
import service.AnimalService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AnimalView {

    private final AnimalService animalService;
    private final Scanner scanner;

    public AnimalView() {
        this.animalService = new AnimalService();
        this.scanner = new Scanner(System.in);
    }

    public void startMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    listAllAnimals();
                    break;
                case 3:
                    listAnimalsByOwnerId();
                    break;
                case 4:
                    deleteAnimal();
                    break;
                case 5:
                    filterAnimalsByName();
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
        System.out.println("\n--- Animal Management Menu ---");
        System.out.println("1. Add New Animal");
        System.out.println("2. List All Animals");
        System.out.println("3. Find Animals by Owner ID");
        System.out.println("4. Delete Animal");
        System.out.println("5. Filter Animals by Name"); // <-- Yeni Menü Başlığı
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

    private void addAnimal() {
        // ... (addAnimal metodu buraya gelecek) ...
    }

    private void listAllAnimals() {
        // ... (listAllAnimals metodu buraya gelecek) ...
    }

    private void deleteAnimal() {
        // ... (deleteAnimal metodu buraya gelecek) ...
    }

    private void listAnimalsByOwnerId() {
        System.out.print("\nEnter Owner ID to view animals: ");
        int ownerId = getUserChoice();

        List<Animal> animals = animalService.getAnimalsByOwner(ownerId);
        if (animals.isEmpty()) {
            System.out.println("No animals found for Owner ID: " + ownerId);
            return;
        }
        System.out.println("\n--- Animals for Owner ID: " + ownerId + " ---");
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    private void filterAnimalsByName() {
        System.out.print("\nEnter Animal Name to filter: ");
        String name = scanner.nextLine();

        List<Animal> animals = animalService.getAnimalsByName(name);
        if (animals.isEmpty()) {
            System.out.println("No animals found matching the name '" + name + "'.");
            return;
        }
        System.out.println("\n--- Filtered Animals ---");
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }
}