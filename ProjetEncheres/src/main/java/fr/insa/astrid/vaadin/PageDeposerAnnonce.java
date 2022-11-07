/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ugobo
 */

// QUAND ON CLIQUE SUR UNE ANNONCE ON TOMBE SUR CA

@Route(value = "pageDeposerAnnonce", layout = MainLayout.class)
@PageTitle("Annonce")

public class PageDeposerAnnonce extends VerticalLayout{
    
    String nomUtilisateur = new String();
    String nomProduit = new String();
    String description = new String();
    String Categorie = new String();
    double prixInital;
    //DateTime
    

    public PageDeposerAnnonce(){
        
        H1 titre = new H1 ("Pour déposer une annonce, il nous faut :");
        
        TextField nomUtilisateur_tf = new TextField("Votre nom d'utilisateur");
        nomUtilisateur_tf.setMaxLength(20);
        nomUtilisateur_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomUtilisateur_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 20);
        });
        
        TextArea nomProduit_tf = new TextArea("Un titre pour votre annonce");
        nomProduit_tf.setSizeFull();
        nomProduit_tf.setMaxLength(50);
        nomProduit_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomProduit_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 50);
        });
        
        TextArea description_tf = new TextArea("Une description de votre produit");
        description_tf.setSizeFull();
        description_tf.setMaxLength(2000);
        description_tf.setValueChangeMode(ValueChangeMode.EAGER);
        description_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 2000);
        });
        
        MultiSelectComboBox comboBox = new MultiSelectComboBox<>("Une catégorie");
        comboBox.setItems("Tout", "Meubles","Vêtements","Bijoux", "Autres");
        
        NumberField prixInitial_tf = new NumberField ("Un prix de départ");
        prixInitial_tf.setWidth("500");
        
        
        DateTimePicker dateFinEnchere_dtp = new DateTimePicker("Une date de fin d'enchère");
        
        Button boutonValider = new Button("Valider");
        boutonValider.setSizeFull();
        boutonValider.addClickListener((t)-> {
           Notification.show("bouton valider clické");
           //nomUtilisateur = nomUtilisateur_tf.
        });
          
        this.add(titre,nomUtilisateur_tf,nomProduit_tf,
                description_tf,comboBox,description_tf,
                dateFinEnchere_dtp,comboBox,prixInitial_tf, boutonValider);
        
    }
    
}
