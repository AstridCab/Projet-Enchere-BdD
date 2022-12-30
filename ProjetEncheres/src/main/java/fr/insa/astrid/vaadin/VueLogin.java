package fr.insa.astrid.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author ugobo
 */

@Route("")
@PageTitle("Connection")
public class VueLogin extends VerticalLayout {

	private final LoginForm connection = new LoginForm();
        private LoginOverlay loginOverlay = new LoginOverlay();

	public VueLogin(){
                
                // pour être beau et au centre
            setSizeFull();
            setAlignItems(FlexComponent.Alignment.CENTER); 
            setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            afficheLogin();
	}

    private void afficheLogin() {
        
        loginOverlay.setOpened(true);
        loginOverlay.setTitle("iBuy");
        loginOverlay.setDescription("Connectez-vous !");
        
        String username = "user";
        String mdp = "pass";
        
        loginOverlay.addLoginListener(event -> {
            String getUsername =  event.getUsername();
            String getMdp = event.getPassword();
            
            if ((getUsername.equals(username)) & (getMdp.equals(mdp))){
                UI.getCurrent().close();
                UI.getCurrent().navigate("Menu");
            
            }else{
                loginOverlay.setError(true);
            }
            
        });      
        
        add(connection,loginOverlay);
        
    }

}
