package tn.esprit.tpfoyer.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.services.FoyerService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@Tag(name = "Gestion foyer")
@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerController {

    private FoyerService foyerService;

    @Operation(description = "retrivez tout les foyers")
    @GetMapping("/retrive-all-foyers")
    public ResponseEntity<List<Foyer>> getAllFoyer() {
        List<Foyer> foyers = foyerService.getAllFoyers();
        return ResponseEntity.ok(foyers);
    }
    @Operation(description = "ajout foyer")
    @PostMapping("/add")
    public ResponseEntity<Foyer> addFoyer(@RequestBody Foyer foyer) {
        Foyer savedFoyer = foyerService.addFoyer(foyer);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedFoyer);
    }

    @PutMapping("/{foyerId}/affect-bloc/{blocId}")
    public Foyer affectBlocToFoyer(@PathVariable Long foyerId, @PathVariable Long blocId) {
        return foyerService.affectBlocToFoyer(blocId, foyerId);
    }

    @PutMapping("/{foyerId}/desaffect-bloc/{blocId}")
    public Foyer desaffectBlocFromFoyer(@PathVariable Long blocId ,@PathVariable Long foyerId) {
        return foyerService.desaffectBlocFromFoyer(blocId, foyerId);
    }

    @Operation(description = "retrivez foyer avec id")
    @GetMapping("/retrive-foyer/{id}")
    public ResponseEntity<Foyer> getFoyerById(@PathVariable Long id) {
        Optional<Foyer> foyer = foyerService.GetFoyerById(id);
        return foyer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(description = "mise a jour foyer")
    @PutMapping("/update-foyer/{id}")
    public ResponseEntity<Foyer> updateFoyer(@PathVariable Long id, @RequestBody Foyer updatedFoyer) {
        try {
            Foyer savedFoyer = foyerService.Update(id, updatedFoyer);
            return ResponseEntity.ok(savedFoyer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "suppression foyer")
    @DeleteMapping("/supression-foyer/{id}")
    public ResponseEntity<Foyer> DeleteFoyer(@PathVariable Long id){
            try {
                foyerService.Delete(id);
                return ResponseEntity.ok().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
    }
}

