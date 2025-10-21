package service;

import dao.AnimalDAO;
import entity.Animal;
import entity.Owner;
import java.util.List;

public class AnimalService {

    private final AnimalDAO animalDAO;
    private final OwnerService ownerService;

    public AnimalService() {
        this.animalDAO = new AnimalDAO();
        this.ownerService = new OwnerService();
    }

    // Değerlendirme formu 18: Animal kaydı eklemeden önce ilişkili Owner'ı kontrol eder.
    public boolean saveAnimal(Animal animal) {
        if (animal.getName() == null || animal.getName().trim().isEmpty()) {
            System.err.println("Validation Error: Animal name cannot be empty.");
            return false;
        }

        Owner existingOwner = ownerService.getOwner(animal.getOwner().getId());
        if (existingOwner == null) {
            System.err.println("Validation Error: Owner ID " + animal.getOwner().getId() + " does not exist.");
            return false;
        }

        animal.setOwner(existingOwner);

        return animalDAO.save(animal);
    }

    public Animal getAnimal(int id) {
        return animalDAO.findById(id);
    }

    public List<Animal> getAllAnimals() {
        return animalDAO.findAll();
    }

    // Değerlendirme formu 14: Belirli bir sahibin hayvanlarını listeler.
    public List<Animal> getAnimalsByOwner(int ownerId) {
        return animalDAO.findByOwnerId(ownerId);
    }

    public List<Animal> getAnimalsByName(String name) {
        return animalDAO.findByName(name);
    }

    // ... (updateAnimal ve deleteAnimal metotları da buraya eklenmeli)
}