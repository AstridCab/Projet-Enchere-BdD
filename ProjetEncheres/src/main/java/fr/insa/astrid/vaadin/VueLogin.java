package fr.insa.astrid.vaadin;

import Objets.Utilisateur;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
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
    
    public VueLogin(){
                
        // pour être beau et au centre
	setSizeFull();
	setAlignItems(FlexComponent.Alignment.CENTER); 
	setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        
        loginOverlay.setTitle("ibuy");
        loginOverlay.setDescription("Notre site de vente aux enchères");
        loginOverlay.setOpened(true);
        
        add(connection,loginOverlay);
        
        testConnection();
        
	}

    private void testConnection() {
        
        loginOverlay.addLoginListener(event -> {         
            try (Connection con = defautConnect()) { 
                String nom = event.getUsername();
                String pass = event.getPassword();
           
//                String username_BDD = "user";
//                String password_BDD = "pass";
                
                Optional<Utilisateur> user = ProjetEncheres.login(con, nom, pass); 
           
//                if ((password.equals(password_BDD)) & (username.equals(username_BDD))) {   
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
        
//    public void doLogin() {
//        try (Connection con = defautConnect()) {
//  
//            Optional<Utilisateur> user = ProjetEncheres.login(con, nom, pass);
//            
//            if(user.isEmpty()) {
//                Notification.show("Utilisateur ou pass invalide");
//            } else {
//                this.main.getSessionInfo().setCurUser(user);
//            }
//            } catch (Exception ex) {
//            throw new Error(ex);
//        }     
//    }

}
