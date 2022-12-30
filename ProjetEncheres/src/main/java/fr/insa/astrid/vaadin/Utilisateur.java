package fr.insa.astrid.vaadin ;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class Utilisateur {
    
    private Integer idUtilisateur;
    
    @NotEmpty
    @NotNull
    private String pseudo;
    
    @NotEmpty
    @NotNull    
    private String prenom = "";

    @NotEmpty
    @NotNull    
    private String nom = "";

    @NotEmpty
    @NotNull    
    private String email = "";
    
    @NotEmpty
    @NotNull    
    private String codePostal = "";
    
    @NotEmpty
    @NotNull
    private String password = "";
    
    
    public Utilisateur(){       
       
    }
    

    
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String adresseMail) {
        this.email = adresseMail;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}


