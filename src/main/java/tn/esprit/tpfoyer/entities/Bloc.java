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
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    private String nomBloc;
    private Long capaciteBloc;

    @ManyToOne
    @JoinColumn(name = "foyer_id")
    private Foyer foyer;

    @OneToMany(mappedBy = "bloc" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Chambre> chambres;

}
