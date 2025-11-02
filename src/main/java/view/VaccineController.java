package view;

import dao.VaccineRepository;
import entity.Vaccine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VaccineService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vaccines")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping
    public ResponseEntity<Vaccine> create(@RequestBody Vaccine vaccine){
        Vaccine result = vaccineService.saveVaccine(vaccine);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Vaccine>> getAll(){
        List<Vaccine> vaccines = vaccineService.getAllVaccines();
        return ResponseEntity.ok(vaccines);
    }

    @GetMapping(params = {"animalId"})
    public ResponseEntity<List<Vaccine>> getAll(@RequestParam(name = "animalId") int animalId){
        List<Vaccine> result = vaccineService.getVaccinesByAnimal(animalId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(params = {"minDate","maxDate"})
    public ResponseEntity<List<Vaccine>> getAll(@RequestParam(name = "minDate") LocalDate minDate,
                                                @RequestParam(name = "maxDate") LocalDate maxDate){
        List<Vaccine> result = vaccineService.getVaccinesByProtectionEndDateRange(minDate, maxDate);
        return ResponseEntity.ok(result);
    }

}
