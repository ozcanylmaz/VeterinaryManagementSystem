package service;

import dao.VetDAO;
import entity.Vet;
import java.util.List;

// Değerlendirme formu 12: Veteriner İş mantığı (Service) katmanı
public class VetService {

    private final VetDAO vetDAO;

    public VetService() {
        this.vetDAO = new VetDAO();
    }

    // Değerlendirme formu 12: Kayıt eklemeden önce iş kuralı kontrolü yapılır.
    public boolean saveVet(Vet vet) {
        if (vet.getName() == null || vet.getName().trim().isEmpty()) {
            System.err.println("Validation Error: Vet name cannot be empty.");
            return false;
        }

        return vetDAO.save(vet);
    }

    public Vet getVet(int id) {
        return vetDAO.findById(id);
    }

    public List<Vet> getAllVets() {
        return vetDAO.findAll();
    }
}