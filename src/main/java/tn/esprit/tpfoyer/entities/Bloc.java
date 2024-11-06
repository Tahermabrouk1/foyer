package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;
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

    @Column(unique = true)
    private String nomBloc;

    @Min(0)
    private Long capaciteBloc;

    @ManyToOne
    @JoinColumn(name = "foyer_id")
    @JsonBackReference
    private Foyer foyer;

    @OneToMany(mappedBy = "bloc" , fetch = FetchType.EAGER)
    private List<Chambre> chambres;
}
