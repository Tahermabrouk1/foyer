package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.respositories.UniversiteRepo;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements UniversiteService{
    private UniversiteRepo universiteRepo;
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
    public Optional<Universite> Delete(Long id) {
        Optional<Universite> universite = universiteRepo.findById(id);
        universite.ifPresent(universiteRepo::delete);
        return universite;
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
}
