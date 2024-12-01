package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation implements Serializable {
    @Id
    private String idReservation;

    //@Pattern(regexp = "^[0-9]{4}$", message = "L'année universitaire doit être au format YYYY")
    //@NotNull
    //@PastOrPresent(message = "L'année universitaire ne peut pas être dans le futur")
    private Date anneeUniversitaire;

    //@NotNull
    private boolean estValide;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Etudiant> etudiants = new ArrayList<Etudiant>() ;
}
