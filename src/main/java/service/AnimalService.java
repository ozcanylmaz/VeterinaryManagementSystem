package service;

import dao.AnimalDAO;
import dao.AnimalRepository;
import entity.Animal;
import entity.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final OwnerService ownerService;

    public AnimalService(AnimalRepository animalRepository, OwnerService ownerService) {
        this.animalRepository = animalRepository;
        this.ownerService = ownerService;
    }

    // Değerlendirme formu 18: Animal kaydı eklemeden önce ilişkili Owner'ı kontrol eder.
    public Animal saveAnimal(Animal animal) {
        if (animal.getName() == null || animal.getName().trim().isEmpty()) {
            throw  new IllegalArgumentException("Validation Error: Animal name cannot be empty.");
        }

        Owner existingOwner = ownerService.getOwner(animal.getOwner().getId());
        if (existingOwner == null) {
            throw new IllegalArgumentException(STR."Validation Error: Owner ID \{animal.getOwner().getId()} does not exist.");
        }

        animal.setOwner(existingOwner);

        return animalRepository.save(animal);
    }

    public Animal getAnimal(int id) {
        Optional<Animal> animal = animalRepository.findById(id);

        if (animal.isEmpty()) {
            throw new IllegalArgumentException("Animal not found");
        }
        return animal.get();
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // Değerlendirme formu 14: Belirli bir sahibin hayvanlarını listeler.
    public List<Animal> getAnimalsByOwner(int ownerId) {
        Owner owner = new Owner();
        owner.setId(ownerId);
        return animalRepository.findAllByOwner(owner);
    }

    public List<Animal> getAnimalsByName(String name) {
        return animalRepository.findAllByName(name);
    }

    // ... (updateAnimal ve deleteAnimal metotları da buraya eklenmeli)
}