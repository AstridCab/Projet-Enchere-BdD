package fr.insa.astrid.vaadin;

import Objets.Utilisateur;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.astrid.projetencheres.ProjetEncheres;
import static fr.insa.astrid.projetencheres.ProjetEncheres.defautConnect;
import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author ugobo
 */

@Route("")
@PageTitle("Connection")
public class VueLogin extends VerticalLayout {

    private final LoginForm connection = new LoginForm();
    LoginOverlay loginOverlay = new LoginOverlay();
    SessionInfo sessionInfo = new SessionInfo();
    Label inscription_lb = new Label("Pas encore inscrit ? Cliquez sur sur le bouton :");
    Button inscription_bt = new Button("Inscription");
    LoginI18n i18n = LoginI18n.createDefault();
    LoginI18n.Form i18nForm = i18n.getForm();
    
    public VueLogin(){
                
        // pour être beau et au centre
	setSizeFull();
	setAlignItems(FlexComponent.Alignment.CENTER); 
	setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        
        loginOverlay.setTitle("ibuy");
        loginOverlay.setDescription("Notre site de vente aux enchères");
        loginOverlay.setOpened(true);
        
        add(loginOverlay);
        
        testConnection();
        inscription();
        
	}

    private void testConnection() {
        
        loginOverlay.addLoginListener(event -> {         
            try (Connection con = defautConnect()) { 
                String nom = event.getUsername();
                String pass = event.getPassword();
                
                Optional<Utilisateur> user = ProjetEncheres.login(con, nom, pass); 
           
                if (user.isEmpty()) {          
                    Notification.show("Mot de passe ou identifiant incorrect");
                    loginOverlay.setError(true); 
               
                } else {  
                    sessionInfo.setCurUser(user);
                    UI.getCurrent().close();
                    UI.getCurrent().navigate("Menu");  
                } 
            } catch (Exception ex) {
            throw new Error(ex);
        }   
        });
    }
    
    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    private void inscription() {
        
        loginOverlay.setI18n(i18n);
        i18nForm.setForgotPassword("Inscription");
        
        loginOverlay.addForgotPasswordListener(click -> {       
            UI.getCurrent().close();
            UI.getCurrent().navigate("Inscription"); 
        });
        
    }

}
