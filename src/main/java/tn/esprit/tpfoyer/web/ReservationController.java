package tn.esprit.tpfoyer.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.services.ReservationService;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Tag(name = "Gestion reservation")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;
    @Operation(description = "retrivez tout les reservations")
    @GetMapping("retrive-all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if(reservations.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(reservations);
        }
    }
    @Operation(description = "ajout reservation")
    @PostMapping("/ajout-reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation savedReservation = reservationService.addReservation(reservation);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedReservation);
    }
    @PutMapping("/{idReservation}/affect-etudiant/{idEtudiant}")
    public Reservation affectEtudiantToReservation(@PathVariable Long idReservation, @PathVariable Long idEtudiant) {
        return reservationService.affectEtudiantToReservation(idReservation, idEtudiant);
    }
    @PutMapping("/{idReservation}/desaffect-etudiant/{idEtudiant}")
    public Reservation deaffectEtudiantToReservation(@PathVariable Long idReservation, @PathVariable Long idEtudiant) {
        return reservationService.deaffectEtudiantToReservation(idReservation, idEtudiant);
    }

    @Operation(description = "retrivez reservations avec id")
    @GetMapping("/retrive-reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> bloc = reservationService.GetReservationById(id);
        return bloc.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(description = "mise a jour  reservations")
    @PutMapping("/update-reservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable long id , @RequestBody Reservation updatedReservation) {
        try {
            Reservation savedReservation = reservationService.Update(id, updatedReservation);
            return ResponseEntity.ok(savedReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "supression reservations")
    @DeleteMapping("/supression-reservation/{id}")
    public ResponseEntity<Void> DeleteReservation(@PathVariable Long id) {
        try {
            reservationService.Delete(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}




