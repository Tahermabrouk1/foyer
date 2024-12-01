package tn.esprit.tpfoyer.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.services.UniversiteService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@Tag(name = "Gestion universite")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UnivertiteController {
    private UniversiteService service;
    @Operation(description = "retrivez tout les  universites")
    @GetMapping("/retrive-all")
    public ResponseEntity<List<Universite>> getAllUniversite() {
        List<Universite> universites = service.getAllUniversites();
        return ResponseEntity.ok(universites);
    }
    @Operation(description = "ajout universite")
    @PostMapping("/ajout-universite")
    public ResponseEntity<Universite> addUniversite(@RequestBody Universite universite) {
        Universite savedUniversite = service.addUniversite(universite);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedUniversite);
    }
    @PutMapping("/{universiteId}/affect-foyer/{foyerId}")
    public Universite affectFoyerToUniversite(@PathVariable Long universiteId, @PathVariable Long foyerId) {
        return service.affectFoyerToUniversite(universiteId, foyerId);
    }

    @PutMapping("/{universiteId}/desaffect-foyer")
    public Universite desaffectFoyerFromUniversite(@PathVariable Long universiteId) {
        return service.desaffectFoyerFromUniversite(universiteId);
    }

    @Operation(description = "retrivez avec universite id")
    @GetMapping("/retrive-universite/{id}")
    public ResponseEntity<Universite> getUniversiteById(@PathVariable Long id) {
        Optional<Universite> universite = service.GetUniversiteById(id);
        return universite.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(description = "mise a jour universite")
    @PutMapping("/update-universite/{id}")
    public ResponseEntity<Universite> updateUniversite(@PathVariable Long id, @RequestBody Universite updatedUniversite) {
        try {
            Universite savedUniversite = service.Update(id, updatedUniversite);
            return ResponseEntity.ok(savedUniversite);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "supression universite")
    @DeleteMapping("/delete-universite/{id}")
    public ResponseEntity<Universite> DeleteUniversite(@PathVariable Long id){
        try {
            service.Delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
