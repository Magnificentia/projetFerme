/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.database;

import app.modules.model.Aliment;
import app.modules.model.Appointment;
import app.modules.model.Bande;
import app.modules.model.BandeVaccine;
import app.modules.model.Batiment;
import app.modules.model.Client;
import app.modules.model.CollecteOeuf;
import app.modules.model.Employes;
import app.modules.model.Fournisseur;
import app.modules.model.Race;
import app.modules.model.Ration;
import app.modules.model.StockAliment;
import app.modules.model.Medicament;
import app.modules.model.Vente;
import app.modules.model.VenteBande;
import app.modules.model.VenteOeuf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import jfxtras.scene.control.agenda.Agenda;

/**
 *
 * @author _Nprime496_
 */
public class DbManager {
   
    private static Connection connection;
    
    public static Connection connect() {  
        //Connection conn = null;
        Connection connect=null;
        try {  
            // db parameters  
            String user="normal";
            String password="123";
            String url = "jdbc:mysql://localhost/volailledor?useLegacyDatetimezone=false&serverTimezone=UTC";  
            // create a connection to the database  
            connect= DriverManager.getConnection(url,user,password);  
              
            System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        return connect;
    } 
    
    
    public static Connection getConnection()
    {
        if(connection==null)
        {
            connection=connect();
        }
        return connection;
    }
    
    
    public static List<Bande> selectBandes()
    {
        List<Bande> stockList = FXCollections.observableArrayList();
        Bande bande;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from bandeview";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    bande = new Bande(result.getString("nomBande"),result.getInt("idbande"),result.getInt("qte"),result.getInt("age"),result.getInt("race_id"),result.getDouble("prix_achat"),result.getString("dateDemarrage"),result.getInt("fourn_id"),result.getString("nom"),result.getString("nomFourn"),result.getString("nomBat"),result.getInt("bat_id"));
                    stockList.add(bande);
                    System.out.println(bande.getDateDemarrage());
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static boolean updateBande(Bande bande)
    {
        try{
            Statement state=getConnection().createStatement();
            String query="update bande set qte="+bande.getQte()+",race_id="+bande.getRace_id()+",prix_achat= "+bande.getPrix_achat()+",prix_vente="+bande.getPrix_vente()+",datedemarrage=\""+bande.getDateDemarrage()+"\",fourn_id="+bande.getFourn_id()+" where idbande="+bande.getIdBande();//bandeview est une vue crée sur la table bande
            System.out.println(query);
            int result=state.executeUpdate(query);
            state.close();
            return result==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static List<Aliment> selectAliments()
    {
        List<Aliment> stockList = FXCollections.observableArrayList();
        Aliment aliment;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from aliment";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    aliment = new Aliment(result.getInt("idali"),result.getString("nomali"),result.getString("description"),result.getDouble("prix"));
                    stockList.add(aliment);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static List<Ration> selectRations()
    {
        List<Ration> stockList = FXCollections.observableArrayList();
        Ration ration;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from rationview";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    ration = new Ration(result.getInt("idration"),result.getInt("ali_id"),result.getInt("bande_id"),result.getString("dateRation"),result.getDouble("qte"),result.getDouble("eau"),result.getString("nomAli"),result.getString("nomration"),result.getString("nomBande"));
                    stockList.add(ration);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static List<BandeVaccine> selectBandeVaccine()
    {
        List<BandeVaccine> stockList = FXCollections.observableArrayList();
        BandeVaccine bv;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from bandevaccineview";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    bv = new BandeVaccine(result.getInt("idbandevaccine"),result.getInt("bande_id"),result.getInt("vaccin_id"),result.getString("datevac"),result.getString("nomBande"),result.getString("nomvac"),result.getString("nomVaccination"));
                    stockList.add(bv);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    
    public static List<Medicament> selectMedicaments()
    {
        List<Medicament> stockList = FXCollections.observableArrayList();
        Medicament bv;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from vaccin";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    bv = new Medicament(result.getInt("idvac"),result.getString("nomVac"),result.getString("periode"),result.getString("description"),result.getDouble("prix"));
                    stockList.add(bv);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static boolean saveMedicaments(Medicament medic)
    {
        try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("insert into vaccin(nomvac,qtevac,periode,description,prix,qtepoule) values (?,?,?,?,?,0)");//bandeview est une vue crée sur la table bande
            query.setString(1,medic.getNomVac());
            query.setInt(2,medic.getQteVac());
            query.setDate(3,null);
            query.setDate(4, null);
            query.setDouble(5, medic.getPrix());
            System.out.println(query.toString());
            int result=query.executeUpdate();
            state.close();
            return result==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static List<StockAliment> selectStockAliment()
    {
        List<StockAliment> stockList = FXCollections.observableArrayList();
        StockAliment bv;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from stockAlimentview";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                
                    bv = new StockAliment(result.getInt("idstock"),result.getInt("qte"),result.getString("dateArrivage"),result.getInt("ali_id"),result.getInt("employe_id"),result.getInt("fourn_id"),result.getString("nomStock"),result.getString("nomAli"),result.getString("nomfourn"));
                    stockList.add(bv);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static List<Fournisseur> selectFournisseurs()
    {
        List<Fournisseur> stockList = FXCollections.observableArrayList();
        Fournisseur bv;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from fournisseur";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    bv = new Fournisseur(result.getInt("idfourn"),result.getString("nomfourn"),result.getString("adresse"),result.getInt("tel"),result.getString("email"),result.getString("siteweb"),result.getInt("typefourn"));
                    stockList.add(bv);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static boolean saveFournisseur(Fournisseur fourn)
    {
        try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("insert into fournisseur(nomfourn,adresse,tel,email,siteweb,typefourn) values (?,?,?,?,?,?)");//bandeview est une vue crée sur la table bande
            query.setString(1,fourn.getNomFourn());
            query.setString(2,fourn.getAdresse());
            query.setInt(3,fourn.getTel());
            query.setString(4,fourn.getEmail());
            query.setString(5, fourn.getSiteweb());
            query.setInt(6, fourn.getTypeFourn());
            System.out.println(query.toString());
            int result=query.executeUpdate();
            state.close();
            return result==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static Fournisseur selectFournisseurById(int id)
    {
        List<Fournisseur> stockList = FXCollections.observableArrayList();
        Fournisseur bv=null;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from fournisseur where idfourn="+id;//bandeview est une vue crée sur la table bande
            
            ResultSet result=state.executeQuery(query);
            result.next();

            bv = new Fournisseur(result.getInt("idfourn"),result.getString("nomfourn"),result.getString("adresse"),result.getInt("tel"),result.getString("email"),result.getString("siteweb"),result.getInt("typefourn"));


            
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return bv;
    }
    
    public static List<CollecteOeuf> selectCollecteOeufs()
    {
        List<CollecteOeuf> stockList = FXCollections.observableArrayList();
        CollecteOeuf col;
        try{
            System.out.println("loading collecte oeuf");
            Statement state=getConnection().createStatement();
            String query="select * from collecteoeuf join typeoeuf on typeoeuf.idtypeoeuf=collecteoeuf.typeoeuf_id;";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    col = new CollecteOeuf(result.getInt("idcollect"),result.getInt("qte"),result.getString("datecollect"),result.getInt("incubation"),result.getInt("bande_id"),result.getInt("qtecasse"),result.getInt("typeoeuf_id"),result.getString("nomTf"));
                    stockList.add(col);
                    
                    System.out.println(col.getDateCollect());
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static boolean saveEmploye(Employes emp)
    {
        try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("insert into employes(nom,user,login,typeem) values (?,?,?,?)");//bandeview est une vue crée sur la table bande
            query.setString(1,emp.getNom());
            query.setString(2,emp.getUser());
            query.setString(3,emp.getLogin());
            query.setString(4,emp.getTypeEm());
            
            System.out.println(query.toString());
            int result=query.executeUpdate();
            state.close();
            return result==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public static List<Race> selectRaces()
    {
        List<Race> stockList = FXCollections.observableArrayList();
        Race col;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from race";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    col = new Race(result.getInt("idrace"),result.getString("nom"),result.getString("description"));
                    stockList.add(col);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static Race selectRaceById(int id)
    {
        Race col=null;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from race where idrace="+id;//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            result.next();
            col = new Race(result.getInt("idrace"),result.getString("nom"),result.getString("description"));
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return col;
    }
    public static List<Employes> selectEmployes()
    {
        List<Employes> stockList = FXCollections.observableArrayList();
        Employes emp;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from employes";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    emp = new Employes(result.getInt("idEm"),result.getString("nom"),result.getString("user"),result.getString("login"),result.getString("typeEm"));
                    stockList.add(emp);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    public static boolean saveCollecte(CollecteOeuf col)
    {
        try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("insert into collecteoeuf(qte,datecollect,incubation,bande_id,qtecasse,typeoeuf_id) values (?,?,?,?,?,?)");//bandeview est une vue crée sur la table bande
            query.setInt(1,col.getQte());
            query.setString(2,col.getDateCollect().toString());
            query.setInt(3,col.getIncubation());
            query.setInt(4, col.getBande_id());
            //query.setDouble(5,col.getPrix_alveole());
            query.setInt(5,col.getQteCasse());
            query.setDouble(6,col.getTypeOeuf());
            
            System.out.println(query.toString());
            int result=query.executeUpdate();
            state.close();
            return result==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public static List<Client> selectClients()
    {
        List<Client> stockList = FXCollections.observableArrayList();
        
        Client emp;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from client";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    emp = new Client(result.getInt("idClient"),result.getString("adresse"),result.getInt("tel"),result.getString("nomclient"));
                    stockList.add(emp);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    public static boolean saveClient(Client cl)
    {
        try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("insert into client(adresse,tel,nomclient) values (?,?,?)");//bandeview est une vue crée sur la table bande
            query.setString(1,cl.getAdresse());
            query.setInt(2,cl.getTel());
            query.setString(3,cl.getNomClient());

            System.out.println(query.toString());
            int result=query.executeUpdate();
            state.close();
            return result==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean deleteRation(Ration ration)
    {
        
        try{
            Statement state=connection.createStatement();
            int r=state.executeUpdate("delete from ration where idmaterials="+ration.getIdration());
            return r==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;    
    }
    

    
    public static boolean deleteBande(Bande bande)
    {
        
        try{
            Statement state=connection.createStatement();
            int r=state.executeUpdate("delete from bande where idbande="+bande.getIdBande());
            return r==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;    
    }
    
    public static boolean deleteAliment(Aliment aliment)
    {
        
        try{
            Statement state=connection.createStatement();
            int r=state.executeUpdate("delete from aliment where idali="+aliment.getIdali());
            return r==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;    
    }
    public static boolean deleteStockAliment(StockAliment aliment)
    {
        
        try{
            Statement state=connection.createStatement();
            int r=state.executeUpdate("delete from stockaliment where idstock="+aliment.getIdStock());
            return r==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        
        return false;    
    }
    
    public static ObservableList<Pair<String,Integer>> getStatsBandedate()
    {
        ObservableList<Pair<String,Integer>> stockList = FXCollections.observableArrayList();
        Pair man;
        try{
            Statement state=connection.createStatement();
            String query="select date_format(datedemarrage,'%Y-%m-%d') date,sum(qte) qte from bande group by datedemarrage";
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    man = new Pair(result.getString("date"),result.getInt("qte"));
                    stockList.add(man);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;     
        
    }

    public static List<Pair<String, Integer>> getBar() {
                ObservableList<Pair<String,Integer>> stockList = FXCollections.observableArrayList();
        Pair man;
        try{
            Statement state=connection.createStatement();
            String query="select nomfourn,count(qte) c from bande join fournisseur on bande.fourn_id=fournisseur.idfourn group by nomfourn";
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    man = new Pair(result.getString("nomfourn"),result.getInt("c"));
                    stockList.add(man);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;  
        
    }
    
    public static List<Batiment> selectBatiments()
    {
        List<Batiment> stockList = FXCollections.observableArrayList();
        
        Batiment emp;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from batiment";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    emp = new Batiment(result.getInt("idbat"),result.getDouble("surface"),result.getString("nombat"));
                    stockList.add(emp);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static Batiment selectBatimentById(int id)
    {
        Batiment bat=null;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from batiment where idbat="+id;//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            result.next();
            bat = new Batiment(result.getInt("idbat"),result.getDouble("surface"),result.getString("nombat"));
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return bat;
    }
    
    
    public static List<VenteOeuf> selectVenteOeuf()
    {
        List<VenteOeuf> stockList = FXCollections.observableArrayList();
        
        VenteOeuf emp;
        try{
            Statement state=getConnection().createStatement();
            String query="select idvenduoeuf,collect_id,concat('venteOeuf',collect_id),client_id,nomclient,idcollect,datevente,total_prix,venduoeuf.qte,employe_id,nom from venduoeuf join client on venduoeuf.client_id=client.idclient join collecteoeuf on collecteoeuf.idcollect=venduoeuf.collect_id join employes on employes.idem=venduoeuf.employe_id";//bandeview est une vue crée sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    emp = new VenteOeuf(result.getInt("collect_id"),result.getInt("idvenduoeuf"),result.getInt("client_id"),result.getString("datevente"),result.getDouble("total_prix"),result.getInt("qte"),result.getInt("employe_id"),result.getString("nom"),result.getString("nomclient"));
                    stockList.add(emp);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static List<VenteBande> selectVentesBande()
    {
        List<VenteBande> stockList = FXCollections.observableArrayList();
        
        VenteBande emp;
        try{
            try (Statement state = getConnection().createStatement()) {
                String query="select bande_id,idvente,client_id,nomclient,datevente,total_prix,qte,employe_id,nom from vendu join client on vendu.client_id=client.idclient join employes on employes.idem=vendu.employe_id;";//bandeview est une vue crée sur la table bande
                ResultSet result=state.executeQuery(query);
                while(result.next()){
                    emp = new VenteBande(result.getInt("bande_id"),result.getInt("idvente"),result.getInt("client_id"), result.getString("datevente"), result.getDouble("total_prix"), result.getInt("qte"),result.getInt("employe_id"), result.getString("nom"), result.getString("nomclient"));
                    stockList.add(emp);
                }
            } //bandeview est une vue crée sur la table bande
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    
    public static Employes connecter(String nom,String login)
    {
        Employes user;
        System.out.println("CONNECTION");
        try{
            Statement state=getConnection().createStatement();
            String query=" select * from employes";
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    System.out.println("test user");
                    user = new Employes(result.getInt("idEm"),result.getString("nom"),result.getString("user"),result.getString("login"),result.getString("typeEm"));

                    if(user.getUser().equals(nom) && user.getLogin().equals(login))
                    {
                        System.out.println("test  ok");
                        return user;
                    }
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean saveStockAliment(StockAliment stockAliment) {
        
        Statement st;
        try {
            st = getConnection().createStatement();
            //System.out.println("everything went fine"+"INSERT INTO USER(user_name,contact) VALUES (\""+user.getUsername()+"\","+user.getContact()+");");
            String query="INSERT INTO stockaliment(qte,datearrivage,ali_id,fourn_id) VALUES ("+stockAliment.getQte()+",\""+stockAliment.getDateArrivage()+"\","+stockAliment.getAli_id()+","+stockAliment.getFourn_id()+" );";
            st.executeUpdate(query);
            
            st.close();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("sorry mon vieux "+ex.getLocalizedMessage());    
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    
    public static boolean saveBande(Bande bande) {
        
        Statement st;
        try {
            st = getConnection().createStatement();
            //System.out.println("everything went fine"+"INSERT INTO USER(user_name,contact) VALUES (\""+user.getUsername()+"\","+user.getContact()+");");
            String query="INSERT INTO bande(qte,age,race_id,prix_achat,datedemarrage,fourn_id,bat_id) VALUES ("+bande.getQte()+",0,"+bande.getRace_id()+","+bande.getPrix_achat()+",\""+bande.getDateDemarrage()+"\","+bande.getFourn_id()+","+bande.getBat_id()+" );";
            System.out.println(query);
            st.executeUpdate(query);
            st.close();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("sorry mon vieux "+ex.getLocalizedMessage());    
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }

    public static boolean saveVenteOeuf(VenteOeuf venteOeuf) {
                Statement st;
        try {
            st = getConnection().createStatement();
            //System.out.println("everything went fine"+"INSERT INTO USER(user_name,contact) VALUES (\""+user.getUsername()+"\","+user.getContact()+");");
            String query="INSERT INTO venduoeuf(collect_id,client_id,datevente,total_prix,qte,employe_id) VALUES ("+venteOeuf.getCollecte_id()+","+venteOeuf.getClient_id()+","+venteOeuf.getDateVente()+","+venteOeuf.getTotal_prix()+","+venteOeuf.getIdVente()+" );";
            System.out.println(query);
            st.executeUpdate(query);
            st.close();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("sorry mon vieux "+ex.getLocalizedMessage());    
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }

    public static int addNewAppointment(Agenda.AppointmentImplLocal newAppointment) {
        try{
            //Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("insert into appointment(starttime,endtime,description) values (?,?,?)",
                                      Statement.RETURN_GENERATED_KEYS);//bandeview est une vue crée sur la table bande
            query.setString(1,newAppointment.getStartLocalDateTime().toString());
            query.setString(2,newAppointment.getEndLocalDateTime().toString());
            query.setString(3,newAppointment.getDescription());

            System.out.println(query.toString());
            int result=query.executeUpdate();
            
            if (result == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = query.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    
                    return generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            finally
            {
                query.close();
            }
            
            
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            finally
        {
            
        }
        
        return 0;
        
    }

    public static void deleteAppointment(int id) {
        try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("delete from appointment where idappoint=?");//bandeview est une vue crée sur la table bande
            query.setInt(1,id);
            System.out.println(query.toString());
            int result=query.executeUpdate();
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        
    }

    public static void updateAppointment(Appointment selectedAppointment) {
               try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("update appointment set starttime=?,endtime=?,description=? where idappoint=?");//bandeview est une vue crée sur la table bande
            query.setString(1,selectedAppointment.getStartLocalDateTime().toString());
            query.setString(2,selectedAppointment.getEndLocalDateTime().toString());
            query.setString(3,selectedAppointment.getDescription());
            query.setInt(4,selectedAppointment.getId());
            System.out.println(query.toString());
            int result=query.executeUpdate();
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        
    }

    public static List<Appointment> getAppointments(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        List appointList=new ArrayList();
        try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("select * FROM Appointment a where a.startTime between ? and ?");//bandeview est une vue crée sur la table bande

            query.setString(1,startLocalDateTime.toString());
            query.setString(2, endLocalDateTime.toString());
            
            System.out.println(query.toString());
            ResultSet result=query.executeQuery();
            while(result.next())
            {
                Appointment app=new Appointment(result.getInt("idappoint"),result.getString("description"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(result.getString("starttime"), formatter);
                app.withStartLocalDateTime(dateTime);
                dateTime = LocalDateTime.parse(result.getString("endtime"), formatter);
                app.withEndLocalDateTime(dateTime);
                app.setSummary(result.getString("description"));
                appointList.add(app);
            }
            
            state.close();
            return appointList;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
        
    }

    public static boolean saveVenteBande(VenteBande col) {
                try{
            Statement state=getConnection().createStatement();
            PreparedStatement query=getConnection().prepareStatement("insert into vendu(client_id,bande_id,datevente,total_prix,qte,employe_id) values (?,?,?,?,?,?)");//bandeview est une vue crée sur la table bande
            query.setInt(1,col.getClient_id());
            query.setInt(2,col.getBande_id());
            query.setString(3,col.getDateVente().toString());
            query.setDouble(4, col.getTotal_prix());
            //query.setDouble(5,col.getPrix_alveole());
            query.setInt(5,col.getQte());
            query.setInt(6,col.getEmploye_id());
            
            System.out.println(query.toString());
            int result=query.executeUpdate();
            state.close();
            return result==1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;//To change body of generated methods, choose Tools | Templates.
    }
}

//dfadfdadsf

//dfadsads


//afdfadsf