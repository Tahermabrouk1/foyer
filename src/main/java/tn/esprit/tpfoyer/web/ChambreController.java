package tn.esprit.tpfoyer.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.services.ChambreService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreController {

    private ChambreService chambreService;

    @GetMapping
    public ResponseEntity<List<Chambre>> getAllChambres() {
        List<Chambre> chambres = chambreService.getAllChambres();
        return ResponseEntity.ok(chambres);
    }

    @PostMapping
    public ResponseEntity<Chambre> addChambre(@RequestBody Chambre chambre) {
        Chambre savedChambre = chambreService.addChambre(chambre);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedChambre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        Optional<Chambre> chambre = chambreService.GetChambreById(id);
        return chambre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable long id ,@RequestBody Chambre updatedChambre) {
        try {
            Chambre savedChambre = chambreService.Update(id , updatedChambre);
            return ResponseEntity.ok(savedChambre);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Chambre> DeleteChambre(@PathVariable Long id){
        return chambreService.Delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
