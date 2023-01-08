/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import Objets.Annonce;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.astrid.projetencheres.ProjetEncheres;
import static fr.insa.astrid.projetencheres.ProjetEncheres.defautConnect;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ugobo
 */

// VUE AVEC TOUTES LES ANNONCES

@Route(value = "vueAnnonces", layout = MainLayout.class)
@PageTitle("Annonces")

public class VueAnnonces extends HorizontalLayout{
    
    //menu déroulant pour trier les annonces
    MultiSelectComboBox comboBox = new MultiSelectComboBox<>();
    AnnonceForm form = new AnnonceForm();
    Grid<Annonce> grid = new Grid<>(Annonce.class);
    HorizontalLayout hLayout = new HorizontalLayout();
    VerticalLayout vLayout = new VerticalLayout();
    TextField filterText = new TextField();
    
    public VueAnnonces(){
        
        setSizeFull();
        
        configureComboBox();
        configureFilterText();
        configureGrid();
        
        hLayout.add(comboBox, filterText);
        vLayout.add(hLayout,grid);
        this.add(vLayout, form);
        
        afficheAnnonces();
        closeEditor();
             
    }

    private void configureFilterText(){
        filterText.setTitle("Filtre");
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.setPlaceholder("Filtrez par nom...");
        filterText.setClearButtonVisible(true);
    }
    
    private void configureComboBox() {
                
        // petit truc déroulant en haut pour trier
        comboBox.setPlaceholder("Catégories");
        comboBox.setItems("Tout", "Meubles","Vêtements","Bijoux", "Autres");
        comboBox.addValueChangeListener(t -> Notification.show(comboBox.getValue().toString()));
        
    }

    private void configureGrid() {
        
        // grille avec toutes les annonces
        grid.setColumns("idAnnonce","nomProduit", "prixActuel",
                "description","dateDebutEnchere",
                "dateFinEnchere");
        grid.setAllRowsVisible(true);
        
        grid.asSingleSelect().addValueChangeListener((event) ->{
           editAnnonce(event.getValue()); 
        });        
    }
    
    private void closeEditor() {
        form.setAnnonce(null);
        form.setVisible(false);      
    }
    
    private void afficheAnnonces() {
                    
        List<Annonce> listeAnnonces = new ArrayList<Annonce>();
        
        try (Connection con = defautConnect()) {
            listeAnnonces = ProjetEncheres.afficherAnnonce(con);       
        } catch (Exception ex) {
            throw new Error(ex);
        }       
        grid.setItems(listeAnnonces);
    }
    
    private void editAnnonce(Annonce annonce) {
        if (annonce == null){
            closeEditor();
        }else{
            form.setAnnonce(annonce);
            form.setVisible(true);
        }
    }
}
