package service;

import dao.AnimalRepository;
import dao.VaccineDAO;
import dao.VaccineRepository;
import entity.Animal;
import entity.Vaccine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;

    public VaccineService(VaccineRepository vaccineRepository, AnimalRepository animalRepository) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
    }

    public Vaccine saveVaccine(Vaccine vaccine) {

        if (vaccine.getName() == null || vaccine.getName().trim().isEmpty() || vaccine.getCode() == null) {
            throw new IllegalArgumentException("Validation Error: Vaccine name and code cannot be empty.");
        }

        Optional<Animal> existingAnimal = animalRepository.findById(vaccine.getAnimal().getId());
        if (existingAnimal.isEmpty()) {
            throw new IllegalArgumentException("Validation Error: Animal ID " + vaccine.getAnimal().getId() + " does not exist.");
        }

        if (vaccine.getProtectionStartDate().isAfter(vaccine.getProtectionEndDate())) {
            throw new IllegalArgumentException("Validation Error: Start date cannot be after end date.");
        }

        vaccine.setAnimal(existingAnimal.get());

        return vaccineRepository.save(vaccine);
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    public List<Vaccine> getVaccinesByAnimal(int animalId) {
        Animal animal = new Animal();
        animal.setId(animalId);
        return vaccineRepository.findAllByAnimal(animal);
    }


    public List<Vaccine> getVaccinesByProtectionEndDateRange(LocalDate start, LocalDate end) {
        return vaccineRepository.findAllByProtectionEndDateBetween(start, end);
    }

}