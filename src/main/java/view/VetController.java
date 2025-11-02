package view;

import entity.Vet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.VetService;

import java.util.List;

@RestController
@RequestMapping("/vets")
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping
    public ResponseEntity<Vet> create(@RequestBody Vet vet){
        Vet result = vetService.saveVet(vet);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Vet>> getAll(){
        List<Vet> vets = vetService.getAllVets();
        return ResponseEntity.ok(vets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vet> getById(@PathVariable(name = "id") int id){
        Vet vet = vetService.getVet(id);
        return ResponseEntity.ok(vet);
    }


}
