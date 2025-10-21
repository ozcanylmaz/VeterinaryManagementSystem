package view;

import entity.Vet;
import service.VetService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Değerlendirme formu 16: Veteriner Yönetimi için kullanıcı arayüzü
public class VetView {

    private final VetService vetService;
    private final Scanner scanner;

    public VetView() {
        this.vetService = new VetService();
        this.scanner = new Scanner(System.in);
    }

    // Ana menü döngüsünü başlatan metot
    public void startMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addVet(); // Yeni veteriner ekleme
                    break;
                case 2:
                    listAllVets(); // Tüm veterinerleri listeleme
                    break;
                case 3:
                    // updateVet() metodu buraya gelecek.
                    System.out.println("Update Vet functionality is not yet implemented.");
                    break;
                case 4:
                    // deleteVet() metodu buraya gelecek.
                    System.out.println("Delete Vet functionality is not yet implemented.");
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
        System.out.println("\n--- Vet Management Menu ---");
        System.out.println("1. Add New Vet");
        System.out.println("2. List All Vets");
        System.out.println("3. Update Vet");
        System.out.println("4. Delete Vet");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    // Kullanıcıdan menü seçimi alan metot (diğer view'larla aynı)
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

    // Değerlendirme formu 7: Yeni veteriner kaydı için kullanıcıdan veri alır ve servise gönderir.
    private void addVet() {
        Vet newVet = new Vet();
        System.out.println("\n--- Add New Vet ---");
        System.out.print("Name: ");
        newVet.setName(scanner.nextLine());

        System.out.print("Phone: ");
        newVet.setPhone(scanner.nextLine());

        System.out.print("Email: ");
        newVet.setMail(scanner.nextLine());

        System.out.print("Address: ");
        newVet.setAddress(scanner.nextLine());

        if (vetService.saveVet(newVet)) {
            System.out.println(" Vet saved successfully!");
        } else {
            System.err.println(" Failed to save vet. Check input data.");
        }
    }

    // Değerlendirme formu 9: Tüm veterinerleri servisten alır ve listeler.
    private void listAllVets() {
        List<Vet> vets = vetService.getAllVets();
        if (vets.isEmpty()) {
            System.out.println("No vets found.");
            return;
        }
        System.out.println("\n--- All Registered Vets ---");
        for (Vet vet : vets) {
            System.out.println(vet.toString());
        }
    }

}