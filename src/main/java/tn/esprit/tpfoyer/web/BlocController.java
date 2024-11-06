package tn.esprit.tpfoyer.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.services.BlocService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocController {

    private BlocService blocService;

    @GetMapping
    public ResponseEntity<List<Bloc>> getAllBlocs() {
        List<Bloc> blocs = blocService.getAllBlocs();
        if(blocs.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(blocs);
        }
    }

    @PostMapping
    public ResponseEntity<Bloc> addBloc(@RequestBody Bloc bloc) {
        Bloc savedBloc = blocService.addBloc(bloc);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedBloc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bloc> getBlocById(@PathVariable Long id) {
        Optional<Bloc> bloc = blocService.GetBlocById(id);
        return bloc.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bloc> updateBloc(@PathVariable long id , @RequestBody Bloc updatedBloc) {
        try {
            Bloc savedBloc = blocService.Update(id, updatedBloc);
            return ResponseEntity.ok(savedBloc);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Bloc> DeleteBloc(@PathVariable Long id){
        return blocService.Delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
