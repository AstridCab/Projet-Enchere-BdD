/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import fr.insa.astrid.vaadin.Annonce;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
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
    TextField prixActuel = new TextField("Votre enchère");
    //NumberField prixEnchere_tf = new NumberField("Prix de votre enchere");
    
    Label labelText = new Label("Pour que vous puissiez enchérir, il nous faut :");
    
    Double prixEnchere;
    
    public Button boutonEncherir = new Button("Enchérir");
    
    HorizontalLayout hLayout = new HorizontalLayout();
    
    Binder<Annonce> binder = new BeanValidationBinder <>(Annonce.class);
    
    
    public AnnonceForm(){
        
        binder.bindInstanceFields(this);
        
        afficheComposants();
        
        encherir();
        
    }
    
    
    public void setAnnonce(Annonce annonce){
        binder.setBean(annonce);
       
    }

    private void afficheComposants() {
        nomProduit.setWidth("20");
        prixActuel.setWidth("20");
        
        boutonEncherir.setWidth("20");    
        boutonEncherir.addThemeVariants(ButtonVariant.LUMO_PRIMARY);        
        boutonEncherir.addClickShortcut(Key.ENTER);

        hLayout.add(boutonEncherir);
        
        this.add(nomProduit, prixActuel,hLayout);
    }

    
    private void encherir() {
        
        boutonEncherir.addClickListener((event) ->{
            //prixEnchere = prixEnchere_tf.getValue();
            
        });
        
    }
    
}
