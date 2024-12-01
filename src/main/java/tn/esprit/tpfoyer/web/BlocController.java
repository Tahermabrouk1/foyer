package tn.esprit.tpfoyer.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.services.BlocService;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Gestion bloc")
@RestController
@RequestMapping("/bloc")
@RequiredArgsConstructor
public class BlocController {

    private final BlocService blocService;

    @Operation(description = "retriver-tout")
    @GetMapping("retrive-all-bloc")
    public ResponseEntity<List<Bloc>> getAllBlocs() {
        List<Bloc> blocs = blocService.getAllBlocs();
        if(blocs.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(blocs);
        }
    }
    @GetMapping("retrive-all-bloc-no-foyer")
    @Scheduled(fixedRate = 120000)
    public ResponseEntity<List<Bloc>> getAllBlocsWithNoFoyer() {
        List<Bloc> blocs = blocService.findAllByFoyerIsNull();
        if(blocs.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(blocs);
        }
    }

    @PutMapping("/{idBloc}/affect-chambre/{idChambre}")
    public Bloc affectChamberToBloc(@PathVariable Long idBloc ,@PathVariable Long idChambre){
        return  blocService.affectChamberToBloc(idBloc , idChambre);
    }

    @PutMapping("/{idBloc}/desaffect-chambre/{idChambre}")
    public Bloc desaffectChamberToBloc(@PathVariable Long idBloc ,@PathVariable Long idChambre){
        return  blocService.deaffectChamberToBloc(idBloc , idChambre);
    }

    @PutMapping("/{idBloc}/affect-Foyer/{idFoyer}")
    public Bloc affectFoyerToBloc(@PathVariable Long idBloc ,@PathVariable Long idFoyer){
        return  blocService.affectFoyerToBloc(idBloc , idFoyer);
    }

    @PutMapping("/{idBloc}/desaffect-Foyer/{idFoyer}")
    public Bloc desaffectFoyerToBloc(@PathVariable Long idBloc ,@PathVariable Long idFoyer){
        return  blocService.deaffectChamberToBloc(idBloc , idFoyer);
    }

    @Operation(description = "ajout bloc")
    @PostMapping("/add")
    public ResponseEntity<Bloc> addBloc(@RequestBody Bloc bloc) {
        Bloc savedBloc = blocService.addBloc(bloc);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedBloc);
    }


    @Operation(description = "retrivez bloc avec id")
    @GetMapping("/retrive-bloc/{id}")
    public ResponseEntity<Bloc> getBlocById(@PathVariable Long id) {
        Optional<Bloc> bloc = blocService.GetBlocById(id);
        return bloc.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(description = "misa a jour bloc")
    @PutMapping("/update-bloc/{id}")
    public ResponseEntity<Bloc> updateBloc(@PathVariable long id , @RequestBody Bloc updatedBloc) {
        try {
            Bloc savedBloc = blocService.Update(id, updatedBloc);
            return ResponseEntity.ok(savedBloc);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "supression bloc")
    @DeleteMapping("/delete-bloc/{id}")
    public ResponseEntity<Void> DeleteBloc(@PathVariable Long id) {
        try {
            blocService.Delete(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
