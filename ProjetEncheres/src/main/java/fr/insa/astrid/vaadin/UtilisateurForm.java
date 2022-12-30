/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import fr.insa.astrid.vaadin.Utilisateur;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

/**
 *
 * @author Ugo
 */
public class UtilisateurForm extends FormLayout{
    
    TextField nom = new TextField("Nom");
    TextField prenom = new TextField("Prenom");
    EmailField email = new EmailField("Email");
    
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");
    
    HorizontalLayout hLayout = new HorizontalLayout();
    
    Binder<Utilisateur> binder = new BeanValidationBinder <>(Utilisateur.class);
    
    
    public UtilisateurForm(){
        
        binder.bindInstanceFields(this);
        
        afficheComposants();
     
    }
    
    
    public void setUtilisateur(Utilisateur utilisateur){
        binder.setBean(utilisateur);
       
    }

    private void afficheComposants() {
        nom.setWidth("20");
        prenom.setWidth("20");
        email.setWidth("20");
        
        save.setWidth("20");
        delete.setWidth("200");
        close.setWidth("200");
        
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        
        hLayout.add(save,delete,close);
        
        this.add(nom, prenom,email,hLayout);
    }
    
}

