/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import Objets.Utilisateur;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.astrid.projetencheres.ProjetEncheres;
import static fr.insa.astrid.projetencheres.ProjetEncheres.defautConnect;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 *
 * @author ugobo
 */

// QUAND ON CLIQUE SUR UNE ANNONCE ON TOMBE SUR CA

@Route(value = "pageDeposerAnnonce", layout = MainLayout.class)
@PageTitle("Annonce")

public class VueDeposerAnnonce extends VerticalLayout{
    
    TextField nomUtilisateur_tf = new TextField("Votre nom d'utilisateur");
    TextArea nomProduit_tf = new TextArea("Un titre pour votre annonce");
    TextArea description_tf = new TextArea("Une description de votre produit");
    MultiSelectComboBox categorie_CB = new MultiSelectComboBox<>("Une catégorie");
    NumberField prixInitial_tf = new NumberField ("Un prix de départ (en euros)");
    DateTimePicker dateDebutEnchere_dtp = new DateTimePicker("Une date de debut d'enchère");
    DateTimePicker dateFinEnchere_dtp = new DateTimePicker("Une date de fin d'enchère");
    Button boutonValider = new Button("Valider");
    
    SessionInfo sessionInfo = new SessionInfo();

    public VueDeposerAnnonce(){
        
        creerAnnonce();
        deposerAnnonce();
        
    }
        
    private void creerAnnonce(){
        
        H1 titre = new H1 ("Pour déposer une annonce, il nous faut :");
               
        nomUtilisateur_tf.setMaxLength(20);
        nomUtilisateur_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomUtilisateur_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 20);
        });
        
        nomProduit_tf.setSizeFull();
        nomProduit_tf.setMaxLength(50);
        nomProduit_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomProduit_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 50);
        });
        
        description_tf.setSizeFull();
        description_tf.setMaxLength(2000);
        description_tf.setValueChangeMode(ValueChangeMode.EAGER);
        description_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 2000);
        });
        
        categorie_CB.setItems("Tout", "Meubles","Vêtements","Bijoux", "Autres");
        
        prixInitial_tf.setWidth("500");
        
        boutonValider.setSizeFull();
        boutonValider.addClickListener((t)-> {
           Notification.show("bouton valider clické");
        });
          
        this.add(titre,nomProduit_tf,
                description_tf,dateDebutEnchere_dtp,dateFinEnchere_dtp,
                categorie_CB,prixInitial_tf, boutonValider);
        
    }
    
    private void deposerAnnonce() {
          
        categorie_CB.addValueChangeListener(e -> {
            String categorie1 = e.getValue().toString();
               
        boutonValider.addClickListener((event) -> {
            try (Connection con = defautConnect()) {

                String nomUtilisateur = nomProduit_tf.getValue();
                Notification.show("connecté !");
                String nomProduit = nomProduit_tf.getValue();
                String description = description_tf.getValue();
                LocalDateTime dateDebutEnchere_ldt = dateDebutEnchere_dtp.getValue();
                Timestamp dateDebutEnchere_ts = Timestamp.valueOf(dateDebutEnchere_ldt);
                LocalDateTime dateFinEnchere_ldt = dateFinEnchere_dtp.getValue();
                Timestamp dateFinEnchere_ts = Timestamp.valueOf(dateFinEnchere_ldt);
                String categorie2 = categorie1;
                
                Notification.show("Annonce "+ nomProduit +" déposée");
    
                Double prixInitial_d = prixInitial_tf.getValue();
                int prixInitial_i = prixInitial_d.intValue();
            
            ProjetEncheres.nouvAnnonce(con, nomUtilisateur, nomProduit, 
                                            description, dateDebutEnchere_ts, dateFinEnchere_ts,
                                            categorie2, prixInitial_i);
            
            } catch (Exception ex) {
            throw new Error(ex);
        }
        });
        });
    }
    
}
