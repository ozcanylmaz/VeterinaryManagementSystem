package service;

import dao.OwnerDAO;
import dao.OwnerRepository;
import entity.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;


    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;

    }

    // Değerlendirme formu 12: Kayıt eklemeden önce iş kuralı kontrolü yapılır.
    public Owner saveOwner(Owner owner) {
        if (owner.getName() == null || owner.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Validation Error: Owner name cannot be empty.");
        }
        if (owner.getMail() != null && !owner.getMail().contains("@")) {
            throw new IllegalArgumentException("Validation Error: Invalid email format.");
        }

        return ownerRepository.save(owner);
    }

    public Owner getOwner(int id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty())
            throw new IllegalArgumentException("Owner Not Found");

        return owner.get();
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    // Owner ismine göre filtreleme
    public List<Owner> getOwnersByName(String name) {
        return ownerRepository.findByName(name);
    }

    public Owner updateOwner(Owner owner) {
        if (owner.getId() <= 0 || ownerRepository.findById(owner.getId()).isEmpty()) {

            throw new IllegalArgumentException("Update Error: Owner with ID " + owner.getId() + " not found.");
        }
        if (owner.getName() == null || owner.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Validation Error: Owner name cannot be empty for update.");
        }

        return ownerRepository.save(owner);
    }

    public void deleteOwner(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Delete Error: Invalid ID provided.");
        }
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Delete Error: Owner with ID " + id + " not found.");
        }

        ownerRepository.delete(owner.get());
    }
}