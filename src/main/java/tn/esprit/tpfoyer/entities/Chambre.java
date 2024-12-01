package tn.esprit.tpfoyer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import tn.esprit.tpfoyer.enums.TypeChambre;

import java.io.Serializable;
import java.util.ArrayList;
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

    //@PositiveOrZero(message = "Le numéro de chambre doit être un nombre positif ou zéro")
    //@NotNull
    private Long numeroChambre;


    @Enumerated(EnumType.STRING)
    //@NotNull
    private TypeChambre typeC;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bloc_id" )
    private Bloc bloc;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chambre_id")
    private List<Reservation> reservations= new ArrayList<Reservation>();
}
