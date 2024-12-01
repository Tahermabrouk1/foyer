package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.respositories.BlocRepo;
import tn.esprit.tpfoyer.respositories.ChambreRepo;
import tn.esprit.tpfoyer.respositories.FoyerRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlocServiceImpl implements BlocService{
    private final BlocRepo BlocRepo;
    private final ChambreRepo chambreRepo;
    private final FoyerRepo foyerRepo;

    @Override
    public Bloc addBloc(Bloc bloc) {
        return BlocRepo.save(bloc);
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return BlocRepo.findAll();
    }

    @Override
    public Optional<Bloc> GetBlocById(Long id) {
        return BlocRepo.findById(id);
    }

    @Override
    public void Delete(Long id) {
        Bloc bloc = BlocRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bloc not found with ID: " + id));
        BlocRepo.delete(bloc);
    }

    @Override
    public Bloc Update(Long id, Bloc bloc) {
        Optional<Bloc> existingBloc = BlocRepo.findById(id);
        if (existingBloc.isPresent()) {
            Bloc findbloc = existingBloc.get();
            findbloc.setNomBloc(bloc.getNomBloc());
            findbloc.setCapaciteBloc(bloc.getCapaciteBloc());
            findbloc.setFoyer(bloc.getFoyer());
            return BlocRepo.save(findbloc);
        } else {
            throw new RuntimeException("Bloc non trouvé avec l'ID: " + id);
        }
    }

    @Override
    public List<Bloc> findAllByFoyerIsNull() {
        return BlocRepo.findAllByFoyerIsNull();
    }


    @Override
    public Bloc affectChamberToBloc(Long idBloc, Long idChambre) {
        Bloc bloc = BlocRepo.findById(idBloc).orElseThrow(() -> new NoSuchElementException("introuvable"));
        Chambre chambre = chambreRepo.findById(idChambre).orElseThrow(() -> new NoSuchElementException("introuvable"));
        bloc.getChambres().add(chambre);
        return BlocRepo.save(bloc);
    }

    @Override
    public Bloc deaffectChamberToBloc(Long idBloc , Long idChambre) {
        Bloc bloc = BlocRepo.findById(idBloc).orElseThrow(() -> new NoSuchElementException("introuvable"));
        Chambre chambre = chambreRepo.findById(idChambre).orElseThrow(() -> new NoSuchElementException("introuvable"));
        bloc.getChambres().remove(chambre);
        return BlocRepo.save(bloc);
    }

    @Override
    public Bloc affectFoyerToBloc(Long idBloc, Long idFoyer) {
        Bloc bloc = BlocRepo.findById(idBloc).orElseThrow(() -> new NoSuchElementException("introuvable"));
        Foyer foyer = foyerRepo.findById(idFoyer).orElseThrow(() -> new NoSuchElementException("introuvable"));
        bloc.setFoyer(foyer);
        return BlocRepo.save(bloc);
    }

    @Override
    public Bloc deaffectFoyerToBloc(Long idBloc) {
        Bloc bloc = BlocRepo.findById(idBloc).orElseThrow(() ->new RuntimeException("introuvable"));
        bloc.setFoyer(null);
        return BlocRepo.save(bloc);
    }





}
