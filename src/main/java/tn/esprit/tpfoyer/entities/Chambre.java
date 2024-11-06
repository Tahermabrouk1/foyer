package tn.esprit.tpfoyer.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.tpfoyer.enums.TypeChambre;

import java.io.Serializable;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;
    private Long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bloc_id" )
    private Bloc bloc;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chambre_id")
    private List<Reservation> reservations;
}
