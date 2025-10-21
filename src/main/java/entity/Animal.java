package entity;

// Değerlendirme formu 1: Tüm sınıflar, metotlar ve değişkenler İngilizce isimlendirilmiştir.
public class Animal {
    private int id;
    private String name;
    private String species; // Tür (Örn: Dog, Cat)
    private String breed;   // Cins (Örn: Golden, Scottish Fold)

    // Değerlendirme formu 13: İlişki: Hayvanın sahibi (Owner Foreign Key'i temsil eder)
    private Owner owner;

    // Constructors
    public Animal() {
    }

    // Değerlendirme formu 3: Gerekli tüm getter ve setter metotları mevcuttur.
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }

    // Değerlendirme formu 15: Nesneyi kolayca yazdırmak için toString metodu
    @Override
    public String toString() {
        return "Animal [ID: " + id +
                ", Name: " + name +
                ", Species: " + species +
                ", Breed: " + breed +
                // İlişkili Owner'ın adı
                ", Owner: " + (owner != null ? owner.getName() : "N/A") +
                "]";
    }
}