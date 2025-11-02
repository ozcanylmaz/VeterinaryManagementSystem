package service;

import dao.VetDAO;
import dao.VetRepository;
import entity.Vet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Değerlendirme formu 12: Veteriner İş mantığı (Service) katmanı
@Service
public class VetService {

    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {

        this.vetRepository = vetRepository;
    }

    // Değerlendirme formu 12: Kayıt eklemeden önce iş kuralı kontrolü yapılır.
    public Vet saveVet(Vet vet) {
        if (vet.getName() == null || vet.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Validation Error: Vet name cannot be empty.");
        }

        return vetRepository.save(vet);
    }

    public Vet getVet(int id) {
        Optional<Vet> vet = vetRepository.findById(id);
        if (vet.isEmpty())
            throw new IllegalArgumentException("Vet Not Found");

        return vet.get();
    }

    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }
}