package view;

import core.DBConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Veritabanı bağlantı testi
        try (Connection conn = DBConnector.getConnection()) {
            System.out.println(" SUCCESS: Database connection established!");

            // Değerlendirme formu 16: Ana menü başlatılıyor
            startApplicationMenu();

        } catch (SQLException e) {
            System.err.println(" ERROR: Database connection failed.");
            System.err.println("Lütfen DBConnector.java dosyasındaki ayarları ve PostgreSQL sunucusunun çalışıp çalışmadığını kontrol edin.");
            e.printStackTrace();
        }
    }

    // Ana uygulama menüsünü yöneten metot
    private static void startApplicationMenu() {
        Scanner mainScanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Owner Management");
            System.out.println("2. Animal Management");
            System.out.println("3. Vet Management");
            System.out.println("4. Appointment Management");
            System.out.println("5. Vaccine Management"); // Vaccine menüsü eklendi
            System.out.println("0. Exit Application");
            System.out.print("Enter your choice: ");

            // Hata Kontrolü
            if (mainScanner.hasNextInt()) {
                int choice = mainScanner.nextInt();
                mainScanner.nextLine(); // Satır sonunu tüket

                switch (choice) {
                    case 1:
                        new OwnerView().startMenu();
                        break;
                    case 2:
                        new AnimalView().startMenu();
                        break;
                    case 3:
                        new VetView().startMenu();
                        break;
                    case 4:
                        new AppointmentView().startMenu();
                        break;
                    case 5:
                        new VaccineView().startMenu(); // Vaccine menüsü çağrılıyor
                        break;
                    case 0:
                        running = false;
                        System.out.println("Application closed.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.err.println("Invalid input. Please enter a number.");
                mainScanner.nextLine(); // Hatalı girişi tüket
            }
        }
        mainScanner.close();
    }
}