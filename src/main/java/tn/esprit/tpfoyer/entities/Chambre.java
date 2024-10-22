package tn.esprit.tpfoyer.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.tpfoyer.enums.TypeChambre;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;
    private Long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    @ManyToOne
    @JoinColumn(name = "bloc_id" )
    private Bloc bloc;

    @OneToMany(mappedBy ="chambre" , fetch = FetchType.EAGER)
    private List<Reservation> reservations;
}
