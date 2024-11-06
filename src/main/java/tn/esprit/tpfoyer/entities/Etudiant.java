package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;

    @Min(value = 2 , message = "not less than 2")
    private String nomEt;

    @Min(value = 2 , message = "not less than 2")
    private String prenomEt;

    @Min(value = 8, message = "not less than 8")
    @NotNull
    private Long cin;

    @Min(value = 3, message = "not less than 3")
    private String ecole;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth is required and should be like this format yyyy-MM-dd")
    private Date dateNaissance;


    @ManyToMany(mappedBy = "etudiants" , cascade = CascadeType.ALL)
    private List<Reservation> reservations ;
}
