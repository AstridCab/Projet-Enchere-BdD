package Objets;

import com.vaadin.flow.component.button.Button;
import java.text.DateFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class Annonce {
    
    @NotEmpty
    private String nomProduit = "";
    
    @NotNull
    @NotEmpty
    private Double prixInitial;
    
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
    private DateFormat dateDebutEnchere;
    
    @NotEmpty
    private DateFormat dateFinEnchere;
    
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

    public DateFormat getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(DateFormat dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
    }

    public DateFormat getDateFinEnchere() {
        return dateFinEnchere;
    }

    public void setDateFinEnchere(DateFormat dateFinEnchere) {
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
    
    public Double getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(Double prixInitial) {
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

}