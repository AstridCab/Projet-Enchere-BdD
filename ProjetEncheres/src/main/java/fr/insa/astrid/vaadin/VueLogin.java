package fr.insa.astrid.vaadin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
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

	public VueLogin(){
                
                // pour être beau et au centre
		setSizeFull();
		setAlignItems(FlexComponent.Alignment.CENTER); 
		setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		// ça envoie sur la page du menu quand on se connecte
                connection.setAction("Menu");  

		VerticalLayout vLayout = new VerticalLayout();
		vLayout.add(new H1("Connection"), new Span("Nom d'utilisateur : user"), new Span("Mot de passe: pass"));
		vLayout.setAlignItems(FlexComponent.Alignment.CENTER);

		add(vLayout, connection);
	}

}
