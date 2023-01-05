/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import Objets.Annonce;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

/**
 *
 * @author Ugo
 */
public class AnnonceForm extends FormLayout{
    
    TextField nomProduit = new TextField("Nom du produit");
    TextField prixInitial = new TextField("Prix actuel");
    NumberField prixEnchere_tf = new NumberField("Votre enchère");
    //NumberField prixEnchere_tf = new NumberField("Prix de votre enchere");
    Label labelText = new Label("Pour que vous puissiez enchérir, il nous faut :");   
    Double prixEnchere;   
    public Button boutonEncherir = new Button("Enchérir");
    HorizontalLayout hLayout = new HorizontalLayout();
    Binder<Annonce> binder = new BeanValidationBinder <>(Annonce.class);
    Annonce annonce = new Annonce();
    
    
    public AnnonceForm(){
        
        binder.bindInstanceFields(this);
        setAnnonce(annonce);
        afficheComposants();
        
        encherir();
        
    }
    
    
    public void setAnnonce(Annonce annonce){
        binder.setBean(annonce);
       
    }

    private void afficheComposants() {
        
        boutonEncherir.setWidth("20");    
        boutonEncherir.addThemeVariants(ButtonVariant.LUMO_PRIMARY);        
        boutonEncherir.addClickShortcut(Key.ENTER);
        
        this.add(nomProduit, prixInitial,prixEnchere_tf,boutonEncherir);
    }

    
    private void encherir() {
        
        boutonEncherir.addClickListener((event) ->{
            //prixEnchere = prixEnchere_tf.getValue();
            
        });
        
    }
    
}
