/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.database;

import app.modules.model.Aliment;
import app.modules.model.Bande;
import app.modules.model.BandeVaccine;
import app.modules.model.CollecteOeuf;
import app.modules.model.Employes;
import app.modules.model.Fournisseur;
import app.modules.model.Ration;
import app.modules.model.StockAliment;
import app.modules.model.Vaccin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 *
 * @author _Nprime496_
 */
public class DbManagerNnane {
   
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
            String query="select * from bandeview";//bandeview est une vue cr�e sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    bande = new Bande(result.getString("nomBande"),result.getInt("idbande"),result.getInt("qte"),result.getInt("age"),result.getInt("race_id"),result.getDouble("prix_achat"),result.getString("dateDemarrage"),result.getInt("fourn_id"),result.getString("nom"),result.getString("nomFourn"),result.getString("nomBat"),result.getInt("bat_id"));
                    stockList.add(bande);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static List<Aliment> selectAliments()
    {
        List<Aliment> stockList = FXCollections.observableArrayList();
        Aliment aliment;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from aliment";//bandeview est une vue cr�e sur la table bande
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
            String query="select * from rationview";//bandeview est une vue cr�e sur la table bande
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
            String query="select * from bandevaccineview";//bandeview est une vue cr�e sur la table bande
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
    
    
    public static List<Vaccin> selectVaccins()
    {
        List<Vaccin> stockList = FXCollections.observableArrayList();
        Vaccin bv;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from vaccin";//bandeview est une vue cr�e sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    bv = new Vaccin(result.getInt("idvac"),result.getString("nomVac"),result.getString("periode"),result.getString("description"),result.getDouble("prix"));
                    stockList.add(bv);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    
    public static List<StockAliment> selectStockAliment()
    {
        List<StockAliment> stockList = FXCollections.observableArrayList();
        StockAliment bv;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from stockAlimentview";//bandeview est une vue cr�e sur la table bande
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
            String query="select * from fournisseur";//bandeview est une vue cr�e sur la table bande
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
    
    public static List<CollecteOeuf> selectCollecteOeufs()
    {
        List<CollecteOeuf> stockList = FXCollections.observableArrayList();
        CollecteOeuf col;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from collecteoeufview";//bandeview est une vue cr�e sur la table bande
            ResultSet result=state.executeQuery(query);
            while(result.next()){
                    col = new CollecteOeuf(result.getInt("idcollect"),result.getInt("qte"),result.getString("datecollect"),result.getInt("incubation"),result.getInt("bande_id"),result.getDouble("prix_alveole"),result.getInt("qtecasse"),result.getInt("typeoeuf_id"),result.getString("nomBande"),result.getString("nomTf"));
                    stockList.add(col);
            }
            state.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return stockList;
    }
    
    public static List<Employes> selectEmployes()
    {
        List<Employes> stockList = FXCollections.observableArrayList();
        Employes emp;
        try{
            Statement state=getConnection().createStatement();
            String query="select * from employes";//bandeview est une vue cr�e sur la table bande
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
    
    public static boolean suppRation(Ration ration)
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
    
    public static boolean suppAliment(Aliment aliment)
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
    
    public static boolean suppStockAliment(StockAliment aliment)
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
    
    
}

//dfadfdadsf

//dfadsads


//afdfadsf