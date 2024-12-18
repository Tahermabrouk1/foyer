package tn.esprit.tpfoyer.services;

import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Chambre;

import java.util.List;
import java.util.Optional;

public interface ChambreService  {
    Chambre addChambre(Chambre chambre);
    List<Chambre> getAllChambres();
    Optional<Chambre> GetChambreById(Long id);
    void Delete (Long id);
    Chambre Update (Long id, Chambre chambre);
    Chambre affectReservationToChambre(Long idReservation , Long idChambre);
    Chambre deaffectReservationToChambre(Long idReservation, Long idChambre);
    Chambre trouverChambreSelonEtudiant(long cin);
}
