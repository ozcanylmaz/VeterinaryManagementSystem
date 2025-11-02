package view;

import entity.Owner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<Owner> create(@RequestBody Owner owner){
        Owner result = ownerService.saveOwner(owner);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAll(){
        List<Owner> owners = ownerService.getAllOwners();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getById(@PathVariable(name = "id") int id){
        Owner owner = ownerService.getOwner(id);
        return ResponseEntity.ok(owner);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<Owner>> getAll(@RequestParam(name = "name") String name){
        List<Owner> owners = ownerService.getOwnersByName(name);
        return ResponseEntity.ok(owners);
    }

    @PutMapping
    public ResponseEntity<Owner> update(@RequestBody Owner owner){
        Owner result = ownerService.updateOwner(owner);
        return ResponseEntity.ok(result);
    }

}
