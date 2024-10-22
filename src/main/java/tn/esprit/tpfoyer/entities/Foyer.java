package tn.esprit.tpfoyer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;
    private String nomFoyer;
    private Long capaciteFoyer;

    @OneToMany(mappedBy = "foyer" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Bloc> bloc ;

    @OneToOne(mappedBy = "foyer" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Universite universite;
}
