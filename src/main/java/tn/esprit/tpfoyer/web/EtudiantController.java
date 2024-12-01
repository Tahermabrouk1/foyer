package tn.esprit.tpfoyer.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.services.EtudiantService;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Gestion etudiant")
@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantController {
    private EtudiantService etudiantService;
    @Operation(description = "retrivez tout les etudiant")
    @GetMapping("/retrive-all-etudiant")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        if(etudiants.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(etudiants);
        }
    }
    @Operation(description = "ajout etudiant")
    @PostMapping("/ajout-etudiant")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedEtudiant);    }

    @Operation(description = "retrivez  etudiant avec id")
    @GetMapping("/retrive-etudiant/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantService.GetEtudiantById(id);
        return etudiant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(description = "mise a jour etudiant")
    @PutMapping("/update-etudiant/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable long id , @RequestBody Etudiant updatedEtudiant) {
        try {
            Etudiant savedEtudiant = etudiantService.Update(id, updatedEtudiant);
            return ResponseEntity.ok(savedEtudiant);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "suppression tout les etudiant")
    @DeleteMapping("/supression-etudiant/{id}")
    public ResponseEntity<Void> DeleteEtudiant(@PathVariable Long id) {
        try {
            etudiantService.Delete(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
