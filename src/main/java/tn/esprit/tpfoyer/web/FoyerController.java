package tn.esprit.tpfoyer.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.services.FoyerService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerController {
    private FoyerService foyerService;

    @GetMapping
    public ResponseEntity<List<Foyer>> getAllFoyer() {
        List<Foyer> foyers = foyerService.getAllFoyers();
        return ResponseEntity.ok(foyers);
    }

    @PostMapping
    public ResponseEntity<Foyer> addBloc(@RequestBody Foyer foyer) {
        Foyer savedFoyer = foyerService.addFoyer(foyer);
        return ResponseEntity.ok(savedFoyer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foyer> getFoyerById(@PathVariable Long id) {
        Optional<Foyer> foyer = foyerService.GetFoyerById(id);
        return foyer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Foyer> updateFoyer(@PathVariable Long id, @RequestBody Foyer updatedFoyer) {
        try {
            Foyer savedFoyer = foyerService.Update(id, updatedFoyer);
            return ResponseEntity.ok(savedFoyer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Foyer> DeleteBloc(@PathVariable Long id){
            try {
                foyerService.Delete(id);
                return ResponseEntity.ok().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
    }
}

