package tn.esprit.tpfoyer.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.services.EtudiantService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantController {
    private EtudiantService etudiantService;

    @GetMapping
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        if(etudiants.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(etudiants);
        }
    }

    @PostMapping
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedEtudiant);    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantService.GetEtudiantById(id);
        return etudiant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable long id , @RequestBody Etudiant updatedEtudiant) {
        try {
            Etudiant savedEtudiant = etudiantService.Update(id, updatedEtudiant);
            return ResponseEntity.ok(savedEtudiant);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Etudiant> DeleteEtudiant(@PathVariable Long id){
        return etudiantService.Delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
