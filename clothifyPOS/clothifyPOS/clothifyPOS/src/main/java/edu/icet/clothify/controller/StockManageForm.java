package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.ProductBo;
import edu.icet.clothify.bo.custom.StockBo;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.dto.StockUpdateDetails;
import edu.icet.clothify.dto.tableModels.StockDetailTable;
import edu.icet.clothify.dto.tableModels.StockTable;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.entity.StockUpdateDetailEntity;
import edu.icet.clothify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class StockManageForm implements Initializable {
    public BorderPane StockPane;
    public TableView tblAllProducts;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public ComboBox cmbFilter;
    public TextField txtProductId;
    public TextField txtName;
    public TextField txtQuantity;
    public Button btnUpdate;
    public TableColumn colUpdateId;
    public TableView tblUpdate;
    public TableColumn colUpdateName;
    public TableColumn colUpdatePrice;
    public TableColumn colUpdateQuantity;
    public TableColumn colUpdateDate;
    public TextField txtStock;

    StockBo stockBo = BoFactory.getInstance().getBo(BoType.STOCK);
    ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUpdate.setDisable(true);
        loadStockTable();
        loadStockDetailTable();
        cmbFilter.getItems().setAll("Low To High","High to Low","Default");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("stock"));
        // Stock Update Detail Table
        colUpdateId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUpdateName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUpdatePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colUpdateQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUpdateDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }



    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/inventoryManagement.fxml")).load();
        StockPane.getChildren().clear();
        StockPane.setCenter(parent);
    }

    public void cmbFilterFunction(ActionEvent actionEvent) {
        String cmbValue = cmbFilter.getValue().toString();

        if(cmbValue.equals("Low To High")){

            ObservableList<Product> tableData  = productBo.getLowToHigh();
            loadStockTable(tableData);

        }else if(cmbValue.equals("High to Low")){

            ObservableList<Product> tableData  = productBo.getHighToLow();
            loadStockTable(tableData);

        }else{

            ObservableList<ProductEntity> tableData  = productBo.getAllProducts();
            ObservableList<Product> data = FXCollections.observableArrayList();
            tableData.forEach(element ->{
                data.add(new ModelMapper().map(element,Product.class));
            });
            loadStockTable(data);

        }
    }

    public void btnSearch(ActionEvent actionEvent) {
        ProductEntity byId = productBo.getById(txtProductId.getText());
        if(!(byId == null)){
            btnUpdate.setDisable(false);
        }
        txtName.setText(byId.getName());
        txtStock.setText(byId.getStock()+"");
    }


    public void btnUpdateFunc(ActionEvent actionEvent) {
        ProductEntity byId = productBo.getById(txtProductId.getText());
        Integer quantity = Integer.parseInt(txtQuantity.getText());
        Integer currentQuantity = byId.getStock();
        Integer updatedQuantity = currentQuantity + quantity;
        byId.setStock(updatedQuantity);
        Boolean isUpdated = productBo.updateEntity(byId);
        StockUpdateDetails stock = new StockUpdateDetails();
        stock.setProductId(byId.getId());
        stock.setName(byId.getName());
        stock.setPrice(byId.getPrice());
        stock.setQuantity(quantity);
        stock.setDate(getLocalDate());
        Boolean isSaved = stockBo.save(stock);
        if(isUpdated && isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Stock Updated Successfully!!").show();
            resetTextFields();
            loadStockTable();
            loadStockDetailTable();
        }
    }

    private String getLocalDate(){
        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        return d.format(date);
    }

    private void loadStockDetailTable() {
        ObservableList<StockUpdateDetails> obList =stockBo.getAllStockDetails();
        ObservableList<StockDetailTable> tableList = FXCollections.observableArrayList();
        obList.forEach(element ->{
            StockDetailTable stockTable = new StockDetailTable(
                    element.getProductId(),
                    element.getName(),
                    element.getPrice(),
                    element.getQuantity(),
                    element.getDate()
            );
            tableList.add(stockTable);
        });
        tblUpdate.setItems(tableList);
    }

    private void loadStockTable() {
        ObservableList<ProductEntity> allProducts = productBo.getAllProducts();
        ObservableList<StockTable> table = FXCollections.observableArrayList();
        allProducts.forEach(element ->{
            if(element.getIsActive()){
                StockTable stockTable = new StockTable(
                        element.getId(),
                        element.getName(),
                        element.getPrice(),
                        element.getStock()
                );
                table.add(stockTable);
            }

        });
        tblAllProducts.setItems(table);
    }
    private void loadStockTable(ObservableList<Product>list) {
        ObservableList<StockTable> table = FXCollections.observableArrayList();
        list.forEach(element ->{
            if(element.getIsActive()){
                StockTable stockTable = new StockTable(
                        element.getId(),
                        element.getName(),
                        element.getPrice(),
                        element.getStock()
                );
                table.add(stockTable);
            }

        });
        tblAllProducts.setItems(table);
    }

    private void resetTextFields() {
        txtName.clear();
        txtStock.clear();
        txtQuantity.clear();
        btnUpdate.setDisable(true);
    }
}
