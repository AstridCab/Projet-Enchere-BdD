/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author ugobo
 */

// VUE AVEC TOUTES LES ANNONCES

@Route(value = "vueAnnonces", layout = MainLayout.class)
@PageTitle("Annonces")

public class VueAnnonces extends VerticalLayout{
    
    //menu déroulant pour trier les annonces
    MultiSelectComboBox comboBox = new MultiSelectComboBox<>("Catégories");
    
    Grid<Annonce> grid = new Grid<>(Annonce.class);
    
    public VueAnnonces(){
        
        setSizeFull();
        
        configureComboBox();
        
        configureGrid();
        this.add(comboBox,grid);
             
    }

    private void configureComboBox() {
                
        // petit truc déroulant en haut pour trier
        comboBox.setItems("Tout", "Meubles","Vêtements","Bijoux", "Autres");
        comboBox.addValueChangeListener(t -> Notification.show(comboBox.getValue().toString()));
        
    }

    private void configureGrid() {
        
        // grille avec toutes les annonces
        grid.setColumns("idAnnonce","nomProduit", "prixInitial",
                "description","etatEnchere","dateDebutEnchere","dateFinEnchere");
        grid.setAllRowsVisible(true);
    }
    
}
