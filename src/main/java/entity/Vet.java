package entity;

// Değerlendirme formu 1: Veteriner varlığını temsil eder.
public class Vet {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String address;

    // Constructors
    public Vet() {}

    // Değerlendirme formu 3: Getter ve Setter metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Değerlendirme formu 15: toString metodu
    @Override
    public String toString() {
        return "Vet [ID: " + id + ", Name: " + name + ", Mail: " + mail + "]";
    }
}