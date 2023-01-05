/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;


import Objets.Utilisateur;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

@Route(value = "vueUtilisateurs", layout = MainLayout.class)
@PageTitle("Utilisateurs")

public class VueUtilisateurs extends HorizontalLayout{
    
    Grid <Utilisateur> grid = new Grid<>(Utilisateur.class);
    TextField filtreRecherche_tf = new TextField();
    UtilisateurForm form = new UtilisateurForm();
    
    
    public VueUtilisateurs(){
        
        setSizeFull();
        
        configureGrid();
        configureFiltreRecherche();
 
        this.add(grid, form);
        afficheUtilisateurs();
        
        closeEditor();
          
    }

    private void configureGrid() {
        
        grid.setSizeFull();
        grid.setColumns("pseudo", "nom", "prenom", 
                "email", "codePostal");
        grid.setAllRowsVisible(true);
        
        grid.asSingleSelect().addValueChangeListener((event) ->{
           editUtilisateur(event.getValue()); 
        });
              
    }

    private void configureFiltreRecherche() {
        filtreRecherche_tf.setPlaceholder("Filtre par nom...");
        filtreRecherche_tf.setClearButtonVisible(true);
        filtreRecherche_tf.setValueChangeMode(ValueChangeMode.LAZY);
    }

    private void afficheUtilisateurs() {
                    
        List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
        
        try (Connection con = defautConnect()) {
            listeUtilisateurs = ProjetEncheres.afficherUtilisateur(con);       
        } catch (Exception ex) {
            throw new Error(ex);
        }
        
        grid.setItems(listeUtilisateurs);
    }

    private void closeEditor() {
        form.setUtilisateur(null);
        form.setVisible(false);
        
    }

    private void editUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null){
            closeEditor();
        }else{
            form.setUtilisateur(utilisateur);
            form.setVisible(true);
        }
    }
    
}