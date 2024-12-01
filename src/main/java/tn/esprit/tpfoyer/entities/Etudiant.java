package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;

    //@Min(value = 2 , message = "minimum 2 lettres")
    //@NotNull
    private String nomEt;

    //@Min(value = 2 , message = "minimum 2 lettres")
    //@NotNull
    private String prenomEt;

    //@Min(value = 8, message = "minimum 8 chiffres")
    //@NotNull
    private Long cin;

    //@Min(value = 3, message = "minimum 3 lettres")
    //@NotNull
    private String ecole;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    //@NotNull(message = "Date of birth is required and should be like this format yyyy-MM-dd")
    private Date dateNaissance;


    @ManyToMany(mappedBy = "etudiants")
    @ToString.Exclude
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<Reservation>();
}
