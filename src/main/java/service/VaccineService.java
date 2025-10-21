package service;

import dao.VaccineDAO;
import entity.Animal;
import entity.Vaccine;
import java.time.LocalDate;
import java.util.List;

public class VaccineService {

    private final VaccineDAO vaccineDAO;
    private final AnimalService animalService;

    public VaccineService() {
        this.vaccineDAO = new VaccineDAO();
        this.animalService = new AnimalService();
    }

    public boolean saveVaccine(Vaccine vaccine) {

        if (vaccine.getName() == null || vaccine.getName().trim().isEmpty() || vaccine.getCode() == null) {
            System.err.println("Validation Error: Vaccine name and code cannot be empty.");
            return false;
        }

        Animal existingAnimal = animalService.getAnimal(vaccine.getAnimal().getId());
        if (existingAnimal == null) {
            System.err.println("Validation Error: Animal ID " + vaccine.getAnimal().getId() + " does not exist.");
            return false;
        }

        if (vaccine.getProtectionStartDate().isAfter(vaccine.getProtectionEndDate())) {
            System.err.println("Validation Error: Start date cannot be after end date.");
            return false;
        }

        vaccine.setAnimal(existingAnimal);

        return vaccineDAO.save(vaccine);
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineDAO.findAll();
    }

    public List<Vaccine> getVaccinesByAnimal(int animalId) {
        return vaccineDAO.findByAnimalId(animalId);
    }


    public List<Vaccine> getVaccinesByProtectionEndDateRange(LocalDate start, LocalDate end) {
        return vaccineDAO.findByProtectionEndDateRange(start, end);
    }

}