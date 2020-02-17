/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.views;

import app.modules.database.DbManager;
import app.modules.model.Invoice;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import static com.itextpdf.text.pdf.PdfName.DEST;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import static javafx.scene.text.FontPosture.REGULAR;
import javafx.scene.text.TextAlignment;

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
        
        
                //menu contextuel
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("supprimer");
        mi1.setOnAction(event->{System.out.println("supp bande");delete(table.getSelectionModel().getSelectedItem());updateData();});
        cm.getItems().add(mi1);
        //MenuItem mi2 = new MenuItem("Menu 2");
        //cm.getItems().add(mi2);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
   });
        
        item=new VBox();item.getChildren().addAll(table,pagination);
    }
    
    public void delete(T object)
    {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public void loadData()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //nothing to do
    }
    protected void updateData()
    {
        pagination.setPageCount((data.size() / rowsPerPage + 1));
        System.out.println("size before"+data.size());
        loadData();
        System.out.println("size after"+data.size());
        //this.table.getItems().clear();
        this.table.setItems(data);
        createPage(pagination.getCurrentPageIndex());
    }
    protected final void createPage(int pageIndex) {

        System.out.println("page index = "+pageIndex);
        int fromIndex = (pageIndex) * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
        System.out.println("subdata from "+fromIndex+" to "+toIndex+" = "+data.subList(fromIndex, toIndex));
        //table.getItems().clear();
        if (fromIndex<=toIndex)table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

        //return new AnchorPane(table);
    }
    
    
    public void createPdf(Invoice invoice) throws FileNotFoundException, DocumentException {

            // 1. Create document
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        // 2. Create PdfWriter
        PdfWriter.getInstance(document, new FileOutputStream("result.pdf"));

        // 3. Open document
        document.open();

        // 4. Add content
        document.add(new Paragraph("Create Pdf Document with iText in Java"));

        // 5. Close document
        document.close();
  }
    
}
