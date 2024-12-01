package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Foyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;

    @Min(value = 2,message = "nom du foyer doit contenir au minimum 2 lettres")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Le nom du bloc ne doit pas contenir des caracteres speciales")
    private String nomFoyer;

    @PositiveOrZero(message = "Capacite doit etre positive")
    private Long capaciteFoyer;

    @OneToMany(mappedBy = "foyer" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Bloc> blocs = new ArrayList<Bloc>();

    @OneToOne(mappedBy = "foyer" , cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Universite universite;
}
