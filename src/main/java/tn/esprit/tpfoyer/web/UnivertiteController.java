package tn.esprit.tpfoyer.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.services.UniversiteService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UnivertiteController {
    private UniversiteService service;
    @GetMapping
    public ResponseEntity<List<Universite>> getAllUniversite() {
        List<Universite> universites = service.getAllUniversites();
        return ResponseEntity.ok(universites);
    }

    @PostMapping
    public ResponseEntity<Universite> addUniversite(@RequestBody Universite universite) {
        Universite savedUniversite = service.addUniversite(universite);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedUniversite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Universite> getUniversiteById(@PathVariable Long id) {
        Optional<Universite> universite = service.GetUniversiteById(id);
        return universite.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Universite> updateUniversite(@PathVariable Long id, @RequestBody Universite updatedUniversite) {
        try {
            Universite savedUniversite = service.Update(id, updatedUniversite);
            return ResponseEntity.ok(savedUniversite);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Universite> DeleteUniversite(@PathVariable Long id){
        try {
            service.Delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
