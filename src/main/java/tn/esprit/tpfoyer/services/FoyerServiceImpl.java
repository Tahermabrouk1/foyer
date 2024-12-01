package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.respositories.BlocRepo;
import tn.esprit.tpfoyer.respositories.FoyerRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements FoyerService{
    private FoyerRepo FoyerRepo;
    private BlocRepo blocRepository;

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
    public void Delete(Long id) {
        Foyer foyer = FoyerRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Foyer not found with ID: " + id));
        FoyerRepo.delete(foyer);
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

    public Foyer affectBlocToFoyer(Long blocId, Long foyerId) {
        Foyer foyer = FoyerRepo.findById(foyerId)
                .orElseThrow(() -> new RuntimeException("Foyer introuvable"));
        Bloc bloc = blocRepository.findById(blocId)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable"));

        foyer.getBlocs().add(bloc);
        return FoyerRepo.save(foyer);
    }

    public Foyer desaffectBlocFromFoyer(Long foyerId , Long blocId) {
        Foyer foyer = FoyerRepo.findById(foyerId)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable"));
        Bloc bloc = blocRepository.findById(blocId)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable"));

        foyer.getBlocs().remove(bloc);
        return FoyerRepo.save(foyer);
    }
}
