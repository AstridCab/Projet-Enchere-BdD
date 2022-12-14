package Objets;

import com.vaadin.flow.component.button.Button;
import java.sql.Timestamp;
import java.text.DateFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class Annonce {
    
    @NotEmpty
    private String nomProduit = "";
    
    @NotNull
    private int prixInitial;
    
    @NotNull
    private int prixActuel;
    
    @NotEmpty
    private String description = "";    

    @NotEmpty
    @NotNull
    private Integer idAnnonce;
    
    @NotEmpty
    @NotNull
    private Integer idOwnerAnnonce;

    @NotEmpty
    @NotNull
    private Integer idType;
    
    @NotEmpty
    private Boolean etatEnchere;
    
    @NotEmpty
    private Timestamp dateDebutEnchere;
    
    @NotEmpty
    private Timestamp dateFinEnchere;
    
    public Button encherir = new Button("Encherir");
    
    
    public Annonce(){

    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Boolean getEtatEnchere() {
        return etatEnchere;
    }

    public void setEtatEnchere(Boolean etatEnchere) {
        this.etatEnchere = etatEnchere;
    }

    public Timestamp getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(Timestamp dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
    }

    public Timestamp getDateFinEnchere() {
        return dateFinEnchere;
    }

    public void setDateFinEnchere(Timestamp dateFinEnchere) {
        this.dateFinEnchere = dateFinEnchere;
    }

    public Integer getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Integer getIdOwnerAnnonce() {
        return idOwnerAnnonce;
    }

    public void setIdOwnerAnnonce(Integer idOwnerAnnonce) {
        this.idOwnerAnnonce = idOwnerAnnonce;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    @Override
    public String toString() {
        return nomProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }
    
    public int getPrixActuel() {
        return prixActuel;
    }

    public void setPrixActuel(int prixActuel) {
        this.prixActuel = prixActuel;
    }

}