package tn.esprit.tpfoyer.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.services.ChambreService;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Gestion chambre")
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreController {

    private ChambreService chambreService;
    @Operation(description = "retrivez chambre")
    @GetMapping("/retrive-all-chambre")
    public ResponseEntity<List<Chambre>> getAllChambres() {
        List<Chambre> chambres = chambreService.getAllChambres();
        return ResponseEntity.ok(chambres);
    }
    @Operation(description = "ajout chambre")
    @PostMapping("/add")
    public ResponseEntity<Chambre> addChambre(@RequestBody Chambre chambre) {
        Chambre savedChambre = chambreService.addChambre(chambre);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedChambre);
    }
    @GetMapping("/retrive-chambre/{cin}")
    public ResponseEntity<Chambre> getChambreByidEtudiant(@PathVariable Long cin){
        return ResponseEntity.ok(chambreService.trouverChambreSelonEtudiant(cin));
    }



    @PutMapping("/{idReservation}/affect-chambre/{idChambre}")
    public Chambre affectReservationToChambre(@PathVariable Long idReservation ,@PathVariable Long idChambre){
        return chambreService.affectReservationToChambre(idReservation, idChambre);
    }

    @PutMapping("/{idReservation}/desaffect-chambre/{idChambre}")
    public Chambre desaffectReservationToChambre(@PathVariable Long idReservation ,@PathVariable Long idChambre){
        return chambreService.deaffectReservationToChambre(idReservation, idChambre);
    }
    @Operation(description = "retivez chambre avec id")
    @GetMapping("/retivez-chambre/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        Optional<Chambre> chambre = chambreService.GetChambreById(id);
        return chambre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(description = "mise a jour chambre")
    @PutMapping("/update-chambre/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable long id ,@RequestBody Chambre updatedChambre) {
        try {
            Chambre savedChambre = chambreService.Update(id , updatedChambre);
            return ResponseEntity.ok(savedChambre);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(description = "supression chambre")
    @DeleteMapping("/delete-chambre/{id}")
    public ResponseEntity<Void> DeleteChambre(@PathVariable Long id) {
        try {
            chambreService.Delete(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
