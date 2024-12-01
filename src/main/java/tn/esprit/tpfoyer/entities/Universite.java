package tn.esprit.tpfoyer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversite;

    @Min(2)
    @NotNull
    private String nomUniversite;

    @Pattern(regexp = "^[a-zA-Z0-9]+", message = "L'adresse ne doit pas contenir des caracteres speciales")
    @NotNull
    private String adresse;

    @OneToOne
    @JoinColumn(name="foyer_id")
    private Foyer foyer ;

}

