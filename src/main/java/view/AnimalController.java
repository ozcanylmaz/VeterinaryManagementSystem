package view;

import entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/")
    public ResponseEntity<Animal> create(@RequestBody Animal animal){
        System.out.println("geldi" + animal);
        Animal result = animalService.saveAnimal(animal);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAll(){
        List<Animal> animals = animalService.getAllAnimals();
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{owner-id}")
    public ResponseEntity<List<Animal>> getAllByOwner(@PathVariable(name = "owner-id") int ownerId){
        List<Animal> animals = animalService.getAnimalsByOwner(ownerId);
        return ResponseEntity.ok(animals);
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<List<Animal>> getAll(@RequestParam(name = "name") String name){
        List<Animal> animals = animalService.getAnimalsByName(name);
        return ResponseEntity.ok(animals);
    }


}
