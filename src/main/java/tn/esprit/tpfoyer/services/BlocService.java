package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Bloc;

import java.util.List;
import java.util.Optional;
public interface BlocService {
    Bloc addBloc(Bloc bloc);
    List<Bloc> getAllBlocs();
    Optional<Bloc> GetBlocById(Long id);
    Optional<Bloc> Delete (Long id);
    Bloc Update (Long id, Bloc bloc);

}
