/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ugobo
 */

// VUE AVEC TOUTES LES ANNONCES

@Route(value = "vueUtilisateurs", layout = MainLayout.class)
@PageTitle("utilisateurs")

public class VueUtilisateurs extends VerticalLayout{
    
    Grid <Personne> grid = new Grid<>(Personne.class);
    
    TextField filtreRecherche_tf = new TextField();
    
    List <Personne> listePersonnes;
   
    
    public VueUtilisateurs(){
        
        setSizeFull();
        
        configureGrid();
        configureFiltreRecherche();
        
        updateGrid();
        
        this.add(grid);
                    
    }

    private void configureGrid() {
        
        grid.setSizeFull();
        grid.setColumns("idUtilisateur", "nom", "prenom", 
                "adresseMail", "codePostal");
        grid.setAllRowsVisible(true);
        
        
    }
    
    public Personne ajoutPersonne(){
        Personne p1 = new Personne() {};
        
        p1.setIdUtilisateur(1);
        p1.setIdAnnonceOwned(2);
        p1.setPrenom("Jean");
        p1.setNom("Michel");
        p1.setAdresseMail("jeanmichel@gmail.com");
        p1.setCodePostal(67000);
        p1.setPassword("pass");
        
        return p1;
    }

    public void updateGrid() {
        Personne p1 = ajoutPersonne();
        listePersonnes.add(p1);
        grid.setItems(listePersonnes);
    }

    private void configureFiltreRecherche() {
        filtreRecherche_tf.setPlaceholder("Filtre par nom...");
        filtreRecherche_tf.setClearButtonVisible(true);
        filtreRecherche_tf.setValueChangeMode(ValueChangeMode.LAZY);
    }
}
