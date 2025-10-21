package entity;

// Değerlendirme formu 1: Tüm sınıflar, metotlar ve değişkenler İngilizce isimlendirilmiştir.
public class Owner {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;

    // Constructors
    public Owner() {}

    public Owner(int id, String name, String phone, String mail, String address, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.city = city;
    }

    // Değerlendirme formu 3: Gerekli tüm getter ve setter metotları mevcuttur.
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

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    // Değerlendirme formu 15: Nesneyi kolayca yazdırmak için toString metodu
    @Override
    public String toString() {
        return "Owner [ID: " + id + ", Name: " + name + ", Mail: " + mail + "]";
    }
}