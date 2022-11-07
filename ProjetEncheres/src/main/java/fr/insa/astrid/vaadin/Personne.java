package fr.insa.astrid.vaadin;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public abstract class Personne {
    
    @NotEmpty
    @NotNull
    private Integer idUtilisateur;
    
    private Integer idAnnonceOwned = 0;
    
    @NotEmpty
    private String prenom = "";

    @NotEmpty
    private String nom = "";

    @Email
    @NotEmpty
    private String adresseMail = "";
    
    @NotEmpty
    private Integer codePostal;
    
    @NotEmpty
    private String password = "";
    
    public Personne(){       
       
    }
    

    
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdAnnonceOwned() {
        return idAnnonceOwned;
    }

    public void setIdAnnonceOwned(Integer idAnnonceOwned) {
        this.idAnnonceOwned = idAnnonceOwned;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

