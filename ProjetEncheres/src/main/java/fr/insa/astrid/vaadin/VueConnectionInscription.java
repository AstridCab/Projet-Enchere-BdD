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

/**
 * vue principale.
 * 
 * @author bonnet
 */

// VUE POUR SE CONNECTER OU S'INSCRIRE

@Route(value = "connection", layout = MainLayout.class)
@PageTitle("Connection | Inscription")

public class VueConnectionInscription extends VerticalLayout {
    
     //deux Layout verticaux 1 connection 1 inscription
    VerticalLayout vLayout0 = new VerticalLayout();
    VerticalLayout vLayout1 = new VerticalLayout();
    VerticalLayout vLayout2 = new VerticalLayout();
    
    HorizontalLayout hLayout = new HorizontalLayout();
    
    TextField nomUtilisateurConnection_tf = new TextField();
    TextField nomInscription_tf = new TextField();
    TextField prenomInscription_tf = new TextField();
    TextField nomUtilisateurInscription_tf = new TextField();
    
    PasswordField passwordConnection_tf = new PasswordField();
    PasswordField passwordInscription_tf = new PasswordField();
    
    Button boutonConnection = new Button("Connection");
    Button boutonInscription = new Button("Inscription");
    
    NumberField codePostal_tf = new NumberField();   
    EmailField adresseMail_tf = new EmailField();    
    
    Label label_vLayout1 = new Label();
    Label label_vLayout2 = new Label();  
    
    public VueConnectionInscription() {

        // nom de la page
        this.add(new H1("Page vaadin test"));
        
        //je veux que ça soit centré bien
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
       

        // deux méthodes pour les colonnes avec connection et inscription
        createConnection();
        createInscription();
        
        createFusionConnectionInscription();   
        
        connection();
        inscription();
        
             
    }

    private void createConnection() {
        //////// VERTICAL LAYOUT 1 CONNECTION \\\\\\\\\\\\\
        
        // Label connection
        label_vLayout1.add("Connection");
        vLayout1.add(label_vLayout1);
        
        //nom d'utilisateur connection
        nomUtilisateurConnection_tf.setLabel("Nom d'utilisateur");
        nomUtilisateurConnection_tf.setMaxLength(20);
        nomUtilisateurConnection_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomUtilisateurConnection_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 20);
        });
        vLayout1.add(nomUtilisateurConnection_tf);
        
        //Mot de passe connection
        passwordConnection_tf.setLabel("Mot de passe");
        //passwordField.setValue("Ex@mplePassw0rd");
        vLayout1.add(passwordConnection_tf);

        //bouton pour connection
        boutonConnection.addClickListener((event) -> {
            Notification.show("Connection");
        });
        vLayout1.add(boutonConnection);
        
    }

    private void createInscription() {
        //////// VERTICAL LAYOUT 2 INSCRIPTION \\\\\\\\\\\\\

        // Label Inscription      
        label_vLayout2.add("Inscription");
        vLayout2.add(label_vLayout2);
        
        //nom d'utilisateur Inscription       
        nomUtilisateurInscription_tf.setLabel("Nom d'utilisateur");
        nomUtilisateurInscription_tf.setMaxLength(20);
        nomUtilisateurInscription_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomUtilisateurInscription_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 20);
        });
        vLayout2.add(nomUtilisateurInscription_tf);
        
                //nom Inscription
        nomInscription_tf.setLabel("Nom");
        nomInscription_tf.setMaxLength(50);
        nomInscription_tf.setValueChangeMode(ValueChangeMode.EAGER);
        nomInscription_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 50);
        });
        vLayout2.add(nomInscription_tf);
        
                //prénom Inscription
        prenomInscription_tf.setLabel("Prenom");
        prenomInscription_tf.setMaxLength(30);
        prenomInscription_tf.setValueChangeMode(ValueChangeMode.EAGER);
        prenomInscription_tf.addValueChangeListener(e -> {
          e.getSource()
                   .setHelperText(e.getValue().length() + "/" + 30);
        });
        vLayout2.add(prenomInscription_tf);

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
        
        //bouton pour Inscription
        boutonInscription.addClickListener((event) -> {
            Notification.show("Inscription");
        });
        vLayout2.add(boutonInscription);
        
    }

    private void createFusionConnectionInscription() {
        //horizontal Layout
        hLayout.setPadding(true);
        hLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        //on fusionne le layout horiazontal avec deux verticaux
        hLayout.add(vLayout0);
        hLayout.add(vLayout1);
        hLayout.add(vLayout2);  
        
        //ajout du horizontal layout à vue principale        
        this.add(hLayout);
    }

    private void inscription() {
        boutonInscription.addClickListener((event) -> {
            String nomUtilisateurInscription = nomUtilisateurInscription_tf.getValue();
            String passwordInscription = passwordInscription_tf.getValue();
            String nomInscription = nomInscription_tf.getValue();
            String prenomInscription = prenomInscription_tf.getValue();
            Double codePostalInscription = codePostal_tf.getValue();
            String adresseMailInscription = adresseMail_tf.getValue();
            
        });
    }

    private void connection() {
        boutonConnection.addClickListener((event) -> {
            String nomUtilisateurConnection = nomUtilisateurConnection_tf.getValue();
            String passwordConnection = passwordConnection_tf.getValue();
        });
    }

    


}
