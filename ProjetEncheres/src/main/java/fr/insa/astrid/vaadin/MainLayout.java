/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

/**
 *
 * @author ugobo
 */

public class MainLayout extends AppLayout{
    
    public MainLayout(){       
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1 ("Site de vente aux enchères");
        
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink vueInscription = new RouterLink("Inscription", VueInscription.class);
        RouterLink vueAnnonces = new RouterLink("Annonces", VueAnnonces.class);
        RouterLink vueMenu = new RouterLink("Menu", Menu.class);
        RouterLink pageDeposerAnnonce = new RouterLink("Déposer une annonce", VueDeposerAnnonce.class);
        RouterLink vueLogin = new RouterLink("Login", VueLogin.class);
        RouterLink vueUtilisateurs = new RouterLink("Utilisateurs", VueUtilisateurs.class);
        
        vueInscription.setHighlightCondition(HighlightConditions.sameLocation());
        vueAnnonces.setHighlightCondition(HighlightConditions.sameLocation());
        vueMenu.setHighlightCondition(HighlightConditions.sameLocation());
        pageDeposerAnnonce.setHighlightCondition(HighlightConditions.sameLocation());
        vueLogin.setHighlightCondition(HighlightConditions.sameLocation());
        vueUtilisateurs.setHighlightCondition(HighlightConditions.sameLocation());
    
        addToDrawer(new VerticalLayout(
                vueLogin,
                vueMenu, 
                vueInscription,
                vueUtilisateurs,
                pageDeposerAnnonce,
                vueAnnonces
        ));

    }
}
    
