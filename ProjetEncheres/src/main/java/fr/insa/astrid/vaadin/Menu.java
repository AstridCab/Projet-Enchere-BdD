/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author ugobo
 */

// PAGE PRINCIPALE QUAND ON LANCE L'APPLI

@Route(value = "Menu", layout = MainLayout.class)
@PageTitle("Menu")

public class Menu extends VerticalLayout{
    
    Label titreMenu = new Label();
    SessionInfo sessionInfo = new SessionInfo();
    
    public Menu(){
        
        titreMenu.setText("Bienvenue sur la page principale de notre site de vente aux enchères");
        setSizeFull();
	setAlignItems(FlexComponent.Alignment.CENTER); 
	setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);        
        this.add(titreMenu);
                
    }
    
    /**
     * @return the sessionInfo
     */
    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }
    
}
