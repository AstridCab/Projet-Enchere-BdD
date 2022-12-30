/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;

import fr.insa.astrid.vaadin.Annonce;
import fr.insa.astrid.vaadin.Utilisateur;
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

public class VueAnnonces extends VerticalLayout{
    
    //menu déroulant pour trier les annonces
    MultiSelectComboBox comboBox = new MultiSelectComboBox<>("Catégories");    
    Grid<Annonce> grid = new Grid<>(Annonce.class); 
    TextField filtreRecherche_tf = new TextField();
    List <Annonce> listeAnnonces = new ArrayList<Annonce>();
    Annonce annonce = new Annonce();
    HorizontalLayout hLayout = new HorizontalLayout();    
    AnnonceForm form = new AnnonceForm();
    
    public VueAnnonces(){
        
        configureComboBox();
        
        hLayout.add(grid,form);
        hLayout.setSizeFull();
        
        setSizeFull();
        
        configureGrid();
        configureFiltreRecherche();

        afficheAnnonces(annonce);
        
        closeEditor();

        add(filtreRecherche_tf,hLayout);
             
    }

    private void configureComboBox() {
                
        // petit truc déroulant en haut pour trier
        comboBox.setItems("Tout", "Meubles","Vêtements","Bijoux", "Autres");
        comboBox.addValueChangeListener(t -> Notification.show(comboBox.getValue().toString()));
        
    }

    private void configureGrid() {
        
        // grille avec toutes les annonces
        grid.setColumns("idAnnonce","nomProduit", "prixInitial",
                "description","etatEnchere","dateDebutEnchere",
                "dateFinEnchere");
        grid.setAllRowsVisible(true);
        
        grid.asSingleSelect().addValueChangeListener((event) -> 
            editAnnonce(event.getValue()));
        
    }
        


    private void configureFiltreRecherche() {
        filtreRecherche_tf.setPlaceholder("Filtre par nom...");
        filtreRecherche_tf.setClearButtonVisible(true);
        filtreRecherche_tf.setValueChangeMode(ValueChangeMode.LAZY);
        
    }

    
    private void afficheAnnonces(Annonce annonce) {
                    
        List<Annonce> listeAnnonces = new ArrayList<Annonce>();
        
        try (Connection con = defautConnect()) {
            listeAnnonces = ProjetEncheres.afficherAnnonce(con, annonce);       
        } catch (Exception ex) {
            throw new Error(ex);
        }
        int L = listeAnnonces.size();
        
        grid.setItems(listeAnnonces);
        }

    
    private void closeEditor() {
        
        form.setAnnonce(null);
        form.setVisible(false);
        
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
