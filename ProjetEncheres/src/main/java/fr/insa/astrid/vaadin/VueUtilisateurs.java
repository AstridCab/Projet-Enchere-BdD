/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.astrid.vaadin;


import Objets.Utilisateur;
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

@Route(value = "vueUtilisateurs", layout = MainLayout.class)
@PageTitle("Utilisateurs")

public class VueUtilisateurs extends VerticalLayout{
    
    Grid <Utilisateur> grid = new Grid<>(Utilisateur.class);
    
    TextField filtreRecherche_tf = new TextField();
    
    List <Utilisateur> listePersonnes = new ArrayList<Utilisateur>();
    
    Utilisateur utilisateur = new Utilisateur();
    
    public VueUtilisateurs(){
        
        setSizeFull();
        
        configureGrid();
        configureFiltreRecherche();
//        
//        updateGrid();
//        
//        UtilisateurForm form = new UtilisateurForm();
//        
//        HorizontalLayout hLayout = new HorizontalLayout(grid,form);
//        hLayout.setSizeFull();
//        
//        VerticalLayout vLayout = new VerticalLayout(filtreRecherche_tf,hLayout);
//        vLayout.setSizeFull();
//        
//        this.add(vLayout);

          //grid.addColumn(Utilisateur::getPseudo);
          afficheUtilisateurs(utilisateur);
          add(grid);
          
    }

    private void configureGrid() {
        
        grid.setSizeFull();
        grid.setColumns("pseudo", "nom", "prenom", 
                "email", "codePostal");
        grid.setAllRowsVisible(true);
              
    }

//    public void updateGrid() {
//        Utilisateur p1 = ajoutPersonne();
//        //listePersonnes.add(p1);
//        //grid.setItems(listePersonnes);
//    }

    private void configureFiltreRecherche() {
        filtreRecherche_tf.setPlaceholder("Filtre par nom...");
        filtreRecherche_tf.setClearButtonVisible(true);
        filtreRecherche_tf.setValueChangeMode(ValueChangeMode.LAZY);
    }

    private void afficheUtilisateurs(Utilisateur utilisateur) {
                    
        List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
        
        try (Connection con = defautConnect()) {
            listeUtilisateurs = ProjetEncheres.afficherUtilisateur(con, utilisateur);       
        } catch (Exception ex) {
            throw new Error(ex);
        }
        int L = listeUtilisateurs.size();
        
        grid.setItems(listeUtilisateurs);
        }
    
}