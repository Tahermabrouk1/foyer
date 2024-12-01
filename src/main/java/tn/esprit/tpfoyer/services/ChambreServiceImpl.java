package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.respositories.ChambreRepo;
import tn.esprit.tpfoyer.respositories.ReservationRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ChambreServiceImpl implements ChambreService{

    private ChambreRepo chambreRepo;
    private ReservationRepo reservationRepo;
    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepo.save(chambre);
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepo.findAll();
    }

    @Override
    public Optional<Chambre> GetChambreById(Long id) {
        return chambreRepo.findById(id);
    }
    @Override
    public void Delete(Long id) {
        Chambre chambre = chambreRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Chambre not found with ID: " + id));
        chambreRepo.delete(chambre);
    }

    @Override
    public Chambre Update(Long id, Chambre chambre) {
        Optional<Chambre> existingChambre = chambreRepo.findById(id);
        if (existingChambre.isPresent()) {
            Chambre findChambre = existingChambre.get();
            findChambre.setNumeroChambre(chambre.getNumeroChambre());
            findChambre.setBloc(chambre.getBloc());
            findChambre.setTypeC(chambre.getTypeC());
            return chambreRepo.save(findChambre);
        } else {
            throw new RuntimeException("Chambre non trouvé avec l'ID: " + id);
        }
    }

    @Override
    public Chambre affectReservationToChambre(Long idReservation, Long idChambre) {
        Reservation reservation = reservationRepo.findById(idReservation).orElseThrow(() -> new NoSuchElementException("introuvable"));
        Chambre chambre = chambreRepo.findById(idChambre).orElseThrow(() -> new NoSuchElementException("introuvable"));
        chambre.getReservations().add(reservation);
        return chambreRepo.save(chambre);
    }

    @Override
    public Chambre deaffectReservationToChambre(Long idReservation, Long idChambre) {
        Reservation reservation = reservationRepo.findById(idReservation).orElseThrow(() -> new NoSuchElementException("introuvable"));
        Chambre chambre = chambreRepo.findById(idChambre).orElseThrow(() -> new NoSuchElementException("introuvable"));
        chambre.getReservations().remove(reservation);
        return chambreRepo.save(chambre);
    }

    @Override
    public Chambre trouverChambreSelonEtudiant(long cin) {
        return chambreRepo.trouverChambreSelonEtudiant(cin);
    }
}
