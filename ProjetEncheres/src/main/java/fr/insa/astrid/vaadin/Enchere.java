/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin ;

import java.text.DateFormat;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author ugobo
 */
public class Enchere {
    
    @NotEmpty
    private DateFormat dateEnchere;
    
    @NotEmpty
    private Double prixActuel;
    
    @NotEmpty
    private Integer idUtilisateur;
    
    @NotEmpty
    private Integer idAnnonce;

    public DateFormat getDateOffre() {
        return dateEnchere;
    }

    public void setDateOffre(DateFormat dateOffre) {
        this.dateEnchere = dateEnchere;
    }

    public Double getPrixActuel() {
        return prixActuel;
    }

    public void setPrixActuel(Double prixActuel) {
        this.prixActuel = prixActuel;
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

