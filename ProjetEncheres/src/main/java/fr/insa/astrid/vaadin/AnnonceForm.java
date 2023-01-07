/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import Objets.Annonce;
import Objets.Enchere;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import fr.insa.astrid.projetencheres.ProjetEncheres;
import static fr.insa.astrid.projetencheres.ProjetEncheres.defautConnect;
import java.sql.Connection;
import java.sql.Timestamp;

/**
 *
 * @author Ugo
 */
public class AnnonceForm extends FormLayout{
    
    TextField nomProduit = new TextField("Nom du produit");
    TextField prixInitial = new TextField("Prix actuel");
    TextField nomUtilisateur_tf = new TextField("Votre nom d'utilisateur");
    NumberField prixEnchere_tf = new NumberField("Votre enchère (en euros)");
    //NumberField prixEnchere_tf = new NumberField("Prix de votre enchere");
    Label labelText = new Label("Pour que vous puissiez enchérir, il nous faut :");     
    public Button boutonEncherir = new Button("Enchérir");
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
        
        boutonEncherir.addThemeVariants(ButtonVariant.LUMO_PRIMARY);        
        boutonEncherir.addClickShortcut(Key.ENTER);
        
        
        this.add(nomProduit, prixInitial,labelText,
                nomUtilisateur_tf,prixEnchere_tf,
                boutonEncherir);
    }

    private void encherir() {
        
        boutonEncherir.addClickListener((click) ->{
            
            if (binder.isValid()){
                Notification.show("ouioui");
               try (Connection con = defautConnect()) {
              
                    Annonce annonce = binder.getBean();
                    int idAnnonce = annonce.getIdAnnonce();
               
                    String nomUtilisateur = nomUtilisateur_tf.getValue();
                    
                    int idUtilisateur = ProjetEncheres.identifiantUtilisateur(con, nomUtilisateur);
                    
                    if (idUtilisateur == -1) {
                        Notification.show("Utilisateur non-inscrit...");
                    }else{
                        
                        int prixEnchere = prixEnchere_tf.getValue().intValue();                       
                        Timestamp dateEnchere = new Timestamp(System.currentTimeMillis());               
                        Enchere enchere = new Enchere();
               
                        enchere.setDateOffre(dateEnchere);
                        enchere.setIdAnnonce(idAnnonce);
                        enchere.setIdUtilisateur(idUtilisateur);
                        enchere.setPrixEnchere(prixEnchere);
                        Notification.show("zizi");
                        ProjetEncheres.nouvOffre(con, annonce, enchere);
                    }
                } catch (Exception ex) {
            throw new Error(ex);
                  }
          }
        });
            }
    
}
