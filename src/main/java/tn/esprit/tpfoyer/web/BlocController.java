package tn.esprit.tpfoyer.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.services.BlocService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/Bloc")
public class BlocController {

    private BlocService blocService;

    @GetMapping
    public ResponseEntity<List<Bloc>> getAllBlocs() {
        List<Bloc> blocs = blocService.getAllBlocs();
        return ResponseEntity.ok(blocs);
    }

    @PostMapping
    public ResponseEntity<Bloc> addBloc(@RequestBody Bloc bloc) {
        Bloc savedBloc = blocService.addBloc(bloc);
        return ResponseEntity.ok(savedBloc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bloc> getBlocById(@PathVariable Long id) {
        Optional<Bloc> bloc = blocService.GetBlocById(id);
        return bloc.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bloc> updateBloc(@PathVariable Long id, @RequestBody Bloc updatedBloc) {
        return blocService.GetBlocById(id)
                .map(bloc -> {
                    bloc.setNomBloc(updatedBloc.getNomBloc());
                    bloc.setCapaciteBloc(updatedBloc.getCapaciteBloc());
                    Bloc savedBloc = blocService.Update(id, bloc);
                    return ResponseEntity.ok(savedBloc);
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Bloc> DeleteBloc(@PathVariable Long id){
        return blocService.Delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
