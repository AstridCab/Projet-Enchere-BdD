package fr.insa.astrid.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.astrid.projetencheres.ProjetEncheres;
import static fr.insa.astrid.projetencheres.ProjetEncheres.defautConnect;
import java.sql.Connection;

/**
 * vue principale.
 * 
 * @author bonnet
 */

// VUE POUR SE CONNECTER OU S'INSCRIRE

@Route(value = "Inscription", layout = MainLayout.class)
@PageTitle("Inscription")

public class VueInscription extends VerticalLayout {
    
     //deux Layout verticaux 1 connection 1 inscription
    VerticalLayout vLayout0 = new VerticalLayout();
    VerticalLayout vLayout1 = new VerticalLayout();
    VerticalLayout vLayout2 = new VerticalLayout();
    VerticalLayout vLayout3 = new VerticalLayout();
    
    HorizontalLayout hLayout = new HorizontalLayout();
    HorizontalLayout hLayout2 = new HorizontalLayout();
    
    TextField nomUtilisateurConnection_tf = new TextField();
    TextField nomInscription_tf = new TextField();
    TextField prenomInscription_tf = new TextField();
    TextField nomUtilisateurInscription_tf = new TextField();
    
    PasswordField passwordConnection_tf = new PasswordField();
    PasswordField passwordInscription_tf = new PasswordField();
    
    Button boutonConnection = new Button("Connection");
    Button boutonInscription = new Button("Inscription");
    
    TextField codePostal_tf = new TextField();   
    EmailField adresseMail_tf = new EmailField();    
    
    Label label_vLayout1 = new Label();
    Label label_vLayout2 = new Label();  
    
    
    public VueInscription() {
        
        //je veux que ça soit centré bien
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);     

        // deux méthodes pour les colonnes avec connection et inscription
        //createConnection();
        createInscription();
        
        inscription();          
    }

    private void createInscription() {
        //////// VERTICAL LAYOUT 2 INSCRIPTION \\\\\\\\\\\\\
        H1 titrePage = new H1 ("Inscription");
        
        //nom d'utilisateur Inscription       
        nomUtilisateurInscription_tf.setLabel("Nom d'utilisateur");
        nomUtilisateurInscription_tf.setMaxLength(20);
        nomUtilisateurInscription_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomUtilisateurInscription_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 20);
        });
        vLayout1.add(nomUtilisateurInscription_tf);
        
                //nom Inscription
        nomInscription_tf.setLabel("Nom");
        nomInscription_tf.setMaxLength(50);
        nomInscription_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomInscription_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 50);
        });
        vLayout1.add(nomInscription_tf);
        
                //prénom Inscription
        prenomInscription_tf.setLabel("Prenom");
        prenomInscription_tf.setMaxLength(30);
        prenomInscription_tf.setValueChangeMode(ValueChangeMode.EAGER);
        prenomInscription_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 30);
        });
        vLayout1.add(prenomInscription_tf);

        //code postal
        codePostal_tf.setLabel("Code postal");
        vLayout2.add(codePostal_tf);
        
        //adresse mail
        adresseMail_tf.setLabel("Adresse mail");
        adresseMail_tf.getElement().setAttribute("name", "email");
        adresseMail_tf.setErrorMessage("Entrer une adresse mail valide");
        adresseMail_tf.setClearButtonVisible(true);   
        vLayout2.add(adresseMail_tf);

        //Mot de passe Inscription
        passwordInscription_tf.setLabel("Mot de passe");
        //passwordField.setValue("Ex@mplePassw0rd");
        vLayout2.add(passwordInscription_tf);
        
        
        add(titrePage);
        hLayout.add(vLayout1);
        hLayout.add(vLayout2);
        add(hLayout);
        add(boutonInscription);
        
    }

    private void inscription() {
        boutonInscription.addClickListener((event) -> {
            try (Connection con = defautConnect()) {

                String nomUtilisateur = nomUtilisateurInscription_tf.getValue();
                String password = passwordInscription_tf.getValue();
                String nom = nomInscription_tf.getValue();
                String prenom = prenomInscription_tf.getValue();
                String codePostal = codePostal_tf.getValue();
                String adresseMail = adresseMail_tf.getValue();
                
                Notification.show("Utilisateur "+ nomUtilisateur +" créé");
            
            ProjetEncheres.nouvUtilisateur(con, nomUtilisateur, nom, prenom, codePostal, nom, nom);
            
            } catch (Exception ex) {
            throw new Error(ex);
        }
        });
        
    }
}
