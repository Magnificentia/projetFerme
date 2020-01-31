/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 *
 * @author _Nprime496_
 */
public class BaseView<T> {
    protected int rowsPerPage=10;
    protected ObservableList<T> data;
    protected TableView<T> table=new TableView<>();
    private Pagination pagination;
    protected VBox item=new VBox();
    public BaseView()
    {
        loadData();
        createPage(0);
        pagination= new Pagination((data.size() / rowsPerPage + 1), 0);
        //pagination.setPageFactory(this::createPage);
        //table.selectionModelProperty().addListener((oldvalue,value,newvalue)->{if(newvalue!=null){ informations.setDisable(false);}else{informations.setDisable(true);}});
        pagination.currentPageIndexProperty().addListener(
                new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("pagination changed");
                createPage(newValue.intValue());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        item=new VBox();item.getChildren().addAll(table,pagination);
    }
    public void loadData()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //nothing to do
    }
    protected void updateData()
    {
        loadData();
        createPage(pagination.getCurrentPageIndex());
    }
    protected final void createPage(int pageIndex) {

        System.out.println("page index = "+pageIndex);
        int fromIndex = (pageIndex) * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size()-1);
        //System.out.println("subdata from "+fromIndex+" to "+toIndex+" = "+data.subList(fromIndex, toIndex));
        //table.getItems().clear();
        if (fromIndex<=toIndex)table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        //return new AnchorPane(table);
    }
    
}
