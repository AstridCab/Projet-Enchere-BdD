/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package fr.insa.astrid.projetencheres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import fr.insa.astrid.lire.Lire;
import java.sql.ResultSet;
import java.sql.Timestamp;


//bonjour

/**
 *
 * @author Utilisateur
 */
public class ProjetEncheres {

    public static void main(String[] args) {
        try ( Connection con = defautConnect()) {
            System.out.println("Vous êtes connecté !");
            menu(con);
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }
        public static Connection connectGeneralPostGres(String host, //methode qui permet de se connecter à une base de données
            int port, String database,
            String user, String pass)
            throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port
                + "/" + database,
                user, pass);
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }

    public static Connection defautConnect()
            throws ClassNotFoundException, SQLException {
        return connectGeneralPostGres("localhost", 5432, "postgres", "postgres", "navet");
    }
     public static void creeSchema(Connection con)
            throws SQLException {
        // je veux que le schema soit entierement créé ou pas du tout
        // je vais donc gérer explicitement une transaction
        con.setAutoCommit(false);
        try ( Statement st = con.createStatement()) {
            // creation des tables
            st.executeUpdate(
                    """
                    create table utilisateur (
                        id integer not null primary key
                        generated always as identity,
                        nom varchar(30) not null unique,
                        pass varchar(30) not null)
                    """);
           // si j'arrive jusqu'ici, c'est que tout s'est bien passé
            // je confirme (commit) la transaction
            // je retourne dans le mode par défaut de gestion des transaction :
            // chaque ordre au SGBD sera considéré comme une transaction indépendante
            //con.setAutoCommit(true);
            st.executeUpdate(
                    """
                    create table annonce (
                        id integer not null primary key
                        generated always as identity,
                        nom varchar(50) not null,
                        description varchar(80) not null,
                        prixInitial integer not null,
                        dateDebutEnchere timestamp,
                        dateFinEnchere timestamp,
                        idUtilisateur integer not null,
                        idType integer not null)
                        """);
            st.executeUpdate(
                    """
                    create table offre (
                        dateOffre timestamp not null,
                        prixActuel integer not null,
                        idUtilisateur integer not null,
                        idAnnonce integer not null)
                        """);
            st.executeUpdate(
                    """
                    create table categorie (
                        id integer not null primary key
                        generated always as identity,
                        type varchar(20))
                        """);
            st.executeUpdate(
                    """
                    alter table annonce
                        add constraint fk_annonce_idUtilisateur
                        foreign key (idUtilisateur) references utilisateur(id)
                    """);
            st.executeUpdate(
                    """
                    alter table annonce
                        add constraint fk_annonce_idType
                        foreign key (idType) references categorie(id)
                    """);
            st.executeUpdate(
                    """
                    alter table offre
                        add constraint fk_offre_idUtilisateur
                        foreign key (idUtilisateur) references utilisateur(id)
                    """);
            st.executeUpdate(
                    """
                    alter table offre
                        add constraint fk_offre_idAnnonce
                        foreign key (idAnnonce) references annonce(id)
                    """);
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            // quelque chose s'est mal passé
            // j'annule la transaction
            con.rollback();
            // puis je renvoie l'exeption pour qu'elle puisse éventuellement
            // être gérée (message à l'utilisateur...)
            throw ex;
        } finally {
            // je reviens à la gestion par défaut : une transaction pour
            // chaque ordre SQL
            con.setAutoCommit(true);
        }
    }
    public static void suppSchema(Connection con) //Permet de supprimer la table utilisateur
            throws SQLException {
        try ( Statement st = con.createStatement()) {
            try {
            st.executeUpdate(
                    """
                    alter table annonce drop constraint fk_annonce_idUtilisateur 
                    """);
            } catch (SQLException ex) {
            }
            try {
            st.executeUpdate(
                    """
                    alter table annonce drop constraint fk_annonce_idType 
                    """);
            } catch (SQLException ex) {
            }
            try {
            st.executeUpdate(
                    """
                    alter table offre drop constraint fk_offre_idUtilisateur 
                    """);
            } catch (SQLException ex) {
            }
            try {
            st.executeUpdate(
                    """
                    alter table offre drop constraint fk_offre_idAnnonce 
                    """);
            } catch (SQLException ex) {
            }
            try {
            st.executeUpdate(
                    """
                    drop table utilisateur
                    """);
            } catch (SQLException ex) {
            }
            try {
            st.executeUpdate(
                    """
                    drop table annonce
                    """);
            } catch (SQLException ex) {
            }
            try {
            st.executeUpdate(
                    """
                    drop table offre
                    """);
            } catch (SQLException ex) {
            }
            try {
            st.executeUpdate(
                    """
                    drop table categorie
                    """);
            con.commit();
            } catch (SQLException ex) {
            }
        }
    }
    public static void recreerSchema(Connection con)
//Permet de faire une méthode qui appelle la methode pour supp et ensuite recree le schéma pour éviter les erreur avec deux fois la même table lorsqu'on la modifie
            throws SQLException {
        suppSchema(con);
        creeSchema(con);
    }
    public static void nouvUtilisateur(Connection con) throws SQLException { // Permet de demander des informations à l'utilisateur qui seront rentrées dans la table
        con.setAutoCommit(false);
        try ( PreparedStatement pst = con.prepareStatement("insert into utilisateur (nom,pass) values (?,?)")) {
            System.out.println("Nom d'utilisateur :");
            String nom = Lire.S();
            System.out.println("Mot de pass :");
            String pass = Lire.S();
            pst.setString(1, nom);
            pst.setString(2, pass);
            pst.executeUpdate();
        } catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally {
            con.setAutoCommit(true);
        }
    }
    public static void nouvAnnonce(Connection con) throws SQLException { // Permet de demander des informations à l'utilisateur qui seront rentrées dans la table Annonce
        con.setAutoCommit(false);
        try ( PreparedStatement pst = con.prepareStatement("insert into annonce (nom,description,prixInitial,dateDebutEnchere,dateFinEnchere,idUtilisateur,idType) values (?,?,?,?,?,?,?)")) {
            System.out.println("Nom de l'objet :");
            String nom = Lire.S();
            System.out.println("Description de l'objet :");
            String description = Lire.S();
            System.out.println("Prix de mise en vente");
            int prixInitial = Lire.i();
            System.out.println("Date de mise en vente (sous la forme AAAA-MM-JJ hh:mm:ss) ");
            String dateDebutEnchere = Lire.S();
            Timestamp dateDebut = Timestamp.valueOf(dateDebutEnchere); // permet de convertir le string en timestamp
            System.out.println("Date de fin de mise en vente (sous la forme AAAA-MM-JJ hh:mm:ss) ");
            String dateFinEnchere = Lire.S();
            Timestamp dateFin = Timestamp.valueOf(dateFinEnchere);
            System.out.println("Nom d'Utilisateur :");
            String nomUtilisateur = Lire.S();
            System.out.println("Mot de pass :");
            String pass = Lire.S();
            System.out.println("Catégorie de votre objet entre vetements,meuble,bijoux,art :");
            String type = Lire.S();
            int idUtilisateur = identifiantUtilisateur(con, nomUtilisateur,pass);
            int idCategorie= identifiantCategorie(con, type);
            pst.setString(1, nom);
            pst.setString(2, description);
            pst.setInt(3, prixInitial);
            pst.setTimestamp(4, dateDebut);
            pst.setTimestamp(5, dateFin);
            pst.setInt(6, idUtilisateur);
            pst.setInt(7, idCategorie);
            pst.executeUpdate();
        } catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally {
            con.setAutoCommit(true);
        }
    }
    public static int identifiantUtilisateur(Connection con, String nomUtilisateur, String pass)throws SQLException {
        con.setAutoCommit(false);
        int id = -1 ; //cela permet au programme de renvoyer une valeur même s'il y a une erreur avec le rollback (-1 car cette valeur est impossible dans le tableau id)
        try ( PreparedStatement pst = con.prepareStatement("select id from utilisateur where (nom,pass) values (?,?)")) {
            pst.setString(1, nomUtilisateur);
            pst.setString(2, pass);
            try (ResultSet tableUtilisateur= pst.executeQuery()){ //on écrit rien dans les parenthèses car le programme sait deja ce qu'il doit envoyer                
                        while (tableUtilisateur.next()){ //tant qu'il reste des lignes le programme continue
                            id = tableUtilisateur.getInt("id");
                        }
            }
        } catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally {
            con.setAutoCommit(true);
        }
    return id ;
    }
    public static int identifiantCategorie(Connection con, String type)throws SQLException {
        con.setAutoCommit(false);
        int id = -1 ; //cela permet au programme de renvoyer une valeur même s'il y a une erreur avec le rollback (-1 car cette valeur est impossible dans le tableau id)
        try ( PreparedStatement pst = con.prepareStatement("select id from categorie where (type) values (?)")) {
            pst.setString(1, type);
            try (ResultSet tableCategorie= pst.executeQuery()){ //on écrit rien dans les parenthèses car le programme sait deja ce qu'il doit envoyer                
                        while (tableCategorie.next()){ //tant qu'il reste des lignes le programme continue
                            id = tableCategorie.getInt("id");
                        }
            }
        } catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally {
            con.setAutoCommit(true);
        }
    return id ;
    }
    public static void categorie(Connection con) throws SQLException { // Permet de demander des informations à l'utilisateur qui seront rentrées dans la table Annonce
        con.setAutoCommit(false);
        try ( PreparedStatement pst = con.prepareStatement("insert into categorie (type) values ('vetements')")) {
            pst.executeUpdate();
        } catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally {
            con.setAutoCommit(true);
        }
        con.setAutoCommit(false);
        try ( PreparedStatement pst = con.prepareStatement("insert into categorie (type) values ('meuble')")) {
            pst.executeUpdate();
        } catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally {
            con.setAutoCommit(true);
        }
        con.setAutoCommit(false);
        try ( PreparedStatement pst = con.prepareStatement("insert into categorie (type) values ('bijoux')")) {
            pst.executeUpdate();
        } catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally {
            con.setAutoCommit(true);
        }
        con.setAutoCommit(false);
        try ( PreparedStatement pst = con.prepareStatement("insert into categorie (type) values ('art')")) {
            pst.executeUpdate();
        } catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally {
            con.setAutoCommit(true);
        }
    }
    public static void afficherCategorie(Connection con) //Permet d'afficher la table categorie
            throws SQLException {
        con.setAutoCommit(false);
        try ( Statement st = con.createStatement()) {
            try (ResultSet tableCategorie=st.executeQuery("select * from categorie")){                
                    System.out.println("Table Categorie");
                while (tableCategorie.next()){ //tant qu'il reste des lignes le programme continue
                    System.out.println("_________________________");
                    int id = tableCategorie.getInt("id");
                    String type = tableCategorie.getString("type");
                    System.out.println(id +"   |   " + type +"   |   "); //Permet d'écrire en ligne
                }
            }
            } catch (SQLException ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }
    }
    public static void afficherUtilisateur(Connection con) //Permet d'afficher la table utilisateur
            throws SQLException {
        con.setAutoCommit(false);
        try ( Statement st = con.createStatement()) {
            try (ResultSet tableUtilisateur=st.executeQuery("select * from Utilisateur")){                
                    System.out.println("Table Utilisateur");
                while (tableUtilisateur.next()){ //tant qu'il reste des lignes le programme continue
                    System.out.println("______________________________________________");
                    int id = tableUtilisateur.getInt("id");
                    String nom = tableUtilisateur.getString("nom");
                    String pass = tableUtilisateur.getString("pass");
                    System.out.println(id +"   |   " + nom +"   |   " + pass); //Permet d'écrire en ligne
                }
            }
            } catch (SQLException ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }
    }
    public static void menu(Connection con){
        int rep = -1;
                while (rep !=0){
                    System.out.println("Menu");
                    System.out.println("=================================");
                    System.out.println("(1) - Recreer la Base de Donnee");
                    System.out.println("(2) - Ajouter un nouvel Utilisateur");
                    System.out.println("(3) - Afficher la table des Utilisateur");
                    System.out.println("(4) - Ajouter une nouvelle Annonce");
                    System.out.println("(5) - Afficher la table des Categories");
                    System.out.println("=================================");
                    System.out.println("Votre choix :");
                    rep= Lire.i();
                    try {
                        if (rep==1) {
                            recreerSchema(con);
                            System.out.println("La Base de Donnee a ete mise a jour");
                        }
                        else if (rep==2) {
                            nouvUtilisateur(con);
                            System.out.println("Bienvenue");
                        }
                        else if (rep==3) {
                            afficherUtilisateur(con);
                        }
                        else if (rep==4) {
                            nouvAnnonce(con);
                            System.out.println("Annonce ajoutée");
                        }
                        else if (rep==5) {
                            categorie(con);
                            afficherCategorie(con);
                        }
                    } catch(SQLException ex){ 
                        throw new Error(ex);
                    }
                }
    }
}

