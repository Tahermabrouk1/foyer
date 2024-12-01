package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;

import java.util.List;
import java.util.Optional;
public interface BlocService {
    Bloc addBloc(Bloc bloc );
    List<Bloc> getAllBlocs();
    Optional<Bloc> GetBlocById(Long id);
    void Delete (Long id);
    Bloc Update (Long id, Bloc bloc);

    List<Bloc> findAllByFoyerIsNull();

    Bloc affectChamberToBloc(Long idBloc , Long idChambre);
    Bloc deaffectChamberToBloc(Long idBloc, Long idChambre);

    Bloc affectFoyerToBloc(Long idBloc , Long idFoyer);
    Bloc deaffectFoyerToBloc(Long idBloc);

}
