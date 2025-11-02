package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

// Değerlendirme formu 1: Aşı varlığını temsil eder.
@Entity
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private int id;
    private String name;
    private String code; //
    private LocalDate protectionStartDate; // Koruma başlangıç tarihi
    private LocalDate protectionEndDate;   // Koruma bitiş tarihi

    // Değerlendirme formu 13: İlişki: Hangi hayvana yapıldığı
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    // Constructors
    public Vaccine() {}

    // Değerlendirme formu 3: Getter ve Setter metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    //  Hata çözüldü: setCode metodu eklendi
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public LocalDate getProtectionStartDate() { return protectionStartDate; }
    public void setProtectionStartDate(LocalDate protectionStartDate) { this.protectionStartDate = protectionStartDate; }
    public LocalDate getProtectionEndDate() { return protectionEndDate; }
    public void setProtectionEndDate(LocalDate protectionEndDate) { this.protectionEndDate = protectionEndDate; }
    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }

    // Değerlendirme formu 15: toString metodu
    @Override
    public String toString() {
        return "Vaccine [ID: " + id + ", Name: " + name + ", Code: " + code + ", Animal: " + (animal != null ? animal.getName() : "N/A") + "]";
    }
}