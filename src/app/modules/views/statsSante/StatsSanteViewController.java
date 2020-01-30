package app.modules.views.statsSante;


import app.modules.IController;
import app.modules.database.DbManager;
import app.modules.userType;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author _Nprime496_
 */
public class StatsSanteViewController implements Initializable,IController {

    @FXML
    private LineChart<String,Integer> grah_qte_date;
    
    
    @FXML
    private PieChart fournAchat;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createLineChart();
        createPiechartAchatFournisseur();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<XYChart.Data> getHistChart()
    {
        List<Pair<String,Integer>> l=DbManager.getStatsBandedate();
        List<XYChart.Data> lr=new ArrayList<>();
        for(Pair<String,Integer> p:l)
        {
            lr.add(new XYChart.Data<String,Integer>(p.getKey(),p.getValue()));
        }
        return lr;
    }
    public void createLineChart()
    {
        ObservableList<XYChart.Data> chartData = FXCollections.observableArrayList(getHistChart()); 
      
        System.out.println(chartData);
        
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setData(chartData);
        
        grah_qte_date.getData().add(dataSeries1);
        
        grah_qte_date.setVisible(true);
              
      //Setting the title of the Pie chart 
        grah_qte_date.setTitle("expences per manufactuer"); 
    }
    
    private List<PieChart.Data> getListChart()
    {
        List<Pair<String,Integer>> l=DbManager.getBar();
        List<PieChart.Data> lr=new ArrayList<>();
        for(Pair<String,Integer> p:l)
        {
            lr.add(new PieChart.Data(p.getKey(),p.getValue()));
        }
        return lr;
    }
    
    public void createPiechartAchatFournisseur()
    {
        //Preparing ObservbleList object         
      ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(getListChart()); 

        
       fournAchat.setData(pieChartData);
              
      //Setting the title of the Pie chart 
      fournAchat.setTitle("Sales per manufactuer"); 
       
      //setting the direction to arrange the data 
      fournAchat.setClockwise(true); 
       
      //Setting the length of the label line 
      fournAchat.setLabelLineLength(50); 

      //Setting the labels of the pie chart visible  
      fournAchat.setLabelsVisible(true); 
   
    }
    @Override
    public Map<Node, List<userType>> getNodeRoles() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
