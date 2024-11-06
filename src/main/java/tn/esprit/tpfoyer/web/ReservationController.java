package tn.esprit.tpfoyer.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.services.ReservationService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if(reservations.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(reservations);
        }
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation savedReservation = reservationService.addReservation(reservation);
        return ResponseEntity
                .created(URI.create("created"))
                .body(savedReservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> bloc = reservationService.GetReservationById(id);
        return bloc.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable long id , @RequestBody Reservation updatedReservation) {
        try {
            Reservation savedReservation = reservationService.Update(id, updatedReservation);
            return ResponseEntity.ok(savedReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> DeleteReservation(@PathVariable Long id){
        return reservationService.Delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
