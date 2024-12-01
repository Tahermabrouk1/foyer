package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.respositories.FoyerRepo;
import tn.esprit.tpfoyer.respositories.UniversiteRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements UniversiteService{

    private UniversiteRepo universiteRepo;
    private FoyerRepo foyerRepo;

    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepo.save(universite);
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepo.findAll();
    }

    @Override
    public Optional<Universite> GetUniversiteById(Long id) {
        return universiteRepo.findById(id);
    }

    @Override
    public void Delete(Long id) {
        Universite universite = universiteRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Universite not found with ID: " + id));
        universiteRepo.delete(universite);
    }

    @Override
    public Universite Update(Long id, Universite universite) {
        Optional<Universite> existingUniversite = universiteRepo.findById(id);
        if (existingUniversite.isPresent()) {
            Universite findUniversite = existingUniversite.get();
            findUniversite.setNomUniversite(universite.getNomUniversite());
            findUniversite.setFoyer(universite.getFoyer());
            findUniversite.setAdresse(universite.getAdresse());
            return universiteRepo.save(findUniversite);
        } else {
            throw new RuntimeException("Bloc non trouvé avec l'ID: " + id);
        }
    }

    @Override
    public Universite affectFoyerToUniversite(Long UniversiteId, Long foyerId) {
        Foyer foyer = foyerRepo.findById(foyerId)
                .orElseThrow(() -> new RuntimeException("Foyer introuvable"));
        Universite universite = universiteRepo.findById(UniversiteId)
                .orElseThrow(() -> new RuntimeException("universite introuvable"));

        universite.setFoyer(foyer);
        return universiteRepo.save(universite);
    }

    @Override
    public Universite desaffectFoyerFromUniversite(Long universiteId) {
        Universite universite = universiteRepo.findById(universiteId)
                .orElseThrow(() -> new RuntimeException("introuvable"));
        universite.setFoyer(null);
        return universiteRepo.save(universite);
    }


}
