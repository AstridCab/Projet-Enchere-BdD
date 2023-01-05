/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objets;

import java.sql.Timestamp;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author ugobo
 */
public class Enchere {
    
    @NotEmpty
    private Timestamp dateEnchere;
    
    @NotEmpty
    private int prixEnchere;
    
    @NotEmpty
    private Integer idUtilisateur;
    
    @NotEmpty
    private Integer idAnnonce;

    public Timestamp getDateOffre() {
        return dateEnchere;
    }

    public void setDateOffre(Timestamp dateOffre) {
        this.dateEnchere = dateEnchere;
    }

    public int getPrixEnchere() {
        return prixEnchere;
    }

    public void setPrixEnchere(int prixActuel) {
        this.prixEnchere = prixActuel;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }
    
    
}
