package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.respositories.FoyerRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements FoyerService{
    private FoyerRepo FoyerRepo;
    @Override
    public Foyer addFoyer(Foyer foyer) {
        return FoyerRepo.save(foyer);
    }

    @Override
    public List<Foyer> getAllFoyers() {
        return FoyerRepo.findAll();
    }

    @Override
    public Optional<Foyer> GetFoyerById(Long id) {
        return FoyerRepo.findById(id);
    }

    @Override
    public Optional<Foyer> Delete(Long id) {
        Optional<Foyer> foyer = FoyerRepo.findById(id);
        foyer.ifPresent(FoyerRepo::delete);
        return foyer;
    }

    @Override
    public Foyer Update(Long id, Foyer foyer) {
        Optional<Foyer> existingFoyer = FoyerRepo.findById(id);
        if (existingFoyer.isPresent()) {
            Foyer findFoyer = existingFoyer.get();
            findFoyer.setNomFoyer(foyer.getNomFoyer());
            findFoyer.setCapaciteFoyer(foyer.getCapaciteFoyer());
            return FoyerRepo.save(findFoyer);
        } else {
            throw new RuntimeException("Bloc non trouvé avec l'ID: " + id);
        }
    }
}
