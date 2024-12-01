package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;

   // @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Le nom du bloc doit contenir uniquement des lettres miniscule, majuscule et des chiffres")
   // @NotNull
    private String nomBloc;

    // @Min(value = 0 , message = "Le nom du bloc ne doit pas etre negative")
    // @NotNull
    private Long capaciteBloc;

    @ManyToOne
    @JoinColumn(name = "foyer_id")
    private Foyer foyer;

    @OneToMany(mappedBy = "bloc")
    @ToString.Exclude
    @JsonIgnore
    private List<Chambre> chambres= new ArrayList<Chambre>();
}
