package fr.insa.astrid.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ugobo
 */

@Route("")
@PageTitle("Connection")
public class VueLogin extends VerticalLayout {

    private final LoginForm connection = new LoginForm();
    LoginOverlay loginOverlay = new LoginOverlay();
    
    public VueLogin(){
                
        // pour être beau et au centre
	setSizeFull();
	setAlignItems(FlexComponent.Alignment.CENTER); 
	setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        // ça envoie sur la page du menu quand on se connecte
        //connection.setAction("Menu");  
	//VerticalLayout vLayout = new VerticalLayout();
	//vLayout.add(new H1("Connection"), new Span("Nom d'utilisateur : user"), new Span("Mot de passe: pass"));
        //vLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        
        loginOverlay.setTitle("ibuy");
        loginOverlay.setDescription("Notre site de vente aux enchères");
        loginOverlay.setOpened(true);
        
        add(connection,loginOverlay);
        
        testConnection();
        
	}

    private void testConnection() {
        
        loginOverlay.addLoginListener(event -> {
           String username = event.getUsername();
           String password = event.getPassword();
           
           String username_BDD = "user";
           String password_BDD = "pass";
           
           if ((password.equals(password_BDD)) & (username.equals(username_BDD))) {            
               UI.getCurrent().close();
               UI.getCurrent().navigate("Menu");

               //Notification.show("c'est ok !");
               
               
           } else {
               Notification.show("Mot de passe ou identifiant incorrect");
               loginOverlay.setError(true);
               
            }
           
        });
        
    }

}
