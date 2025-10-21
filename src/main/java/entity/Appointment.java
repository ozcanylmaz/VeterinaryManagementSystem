package entity;

import java.time.LocalDateTime;

// Değerlendirme formu 1: Randevu varlığını temsil eder.
public class Appointment {
    private int id;
    private LocalDateTime appointmentDate; // Randevu tarihi ve saati

    // Değerlendirme formu 13: İlişkiler
    private Animal animal; // Hangi hayvan
    private Vet vet;       // Hangi veteriner

    // Constructors
    public Appointment() {}
    // Gerekli tüm constructor'ları eklemeyi unutmayın!

    // Değerlendirme formu 3: Getter ve Setter metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }

    // İlişki Metotları
    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }

    public Vet getVet() { return vet; }
    public void setVet(Vet vet) { this.vet = vet; }

    // Değerlendirme formu 15: toString metodu
    @Override
    public String toString() {
        return "Appointment [ID: " + id +
                ", Date: " + appointmentDate +
                ", Animal: " + (animal != null ? animal.getName() : "N/A") +
                ", Vet: " + (vet != null ? vet.getName() : "N/A") + "]";
    }
}