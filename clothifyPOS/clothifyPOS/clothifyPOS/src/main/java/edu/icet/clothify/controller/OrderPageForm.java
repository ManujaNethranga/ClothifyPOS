package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.ProductBo;
import edu.icet.clothify.dto.tableModels.CartTable;
import edu.icet.clothify.dto.tableModels.RemoveTable;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderPageForm implements Initializable {
    public BorderPane OrderPane;
    public TableView tblOrder;
    public TableColumn colItemNo;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TextField txtPaidAmount;
    public RadioButton RadioCash;
    public RadioButton RadioCard;
    public TextField txtSubTotal;
    public TextField txtGrandTotal;
    public TextField txtProductId;
    public Button btnSearch;
    public TextField txtName;
    public TextField txtSize;
    public TextField txtPrice;
    public TextField txtStock;
    public TextField txtRemove;
    public TextField txtQuantity;
    public TableView tblRemove;
    public TableColumn colItemNo1;
    public TableColumn colItemName1;
    public TableColumn colQty1;
    public TableColumn colTotal1;
    public TextField txtBalance;
    public TextField txtDiscount;
    public TextField txtDiscount1;
    public TableColumn colDiscount;
    public TableColumn colUnitPrice1;
    public Button btnRemoveId;

    ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);



    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent  = new FXMLLoader(getClass().getResource("/view/AdminDashboardPage.fxml")).load();
        OrderPane.getChildren().clear();
        OrderPane.setCenter(parent);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemNo.setCellValueFactory(new PropertyValueFactory<>("itemNo"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        colItemName1.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemNo1.setCellValueFactory(new PropertyValueFactory<>("itemNo"));
        colQty1.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice1.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal1.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    public void btnSearch(ActionEvent actionEvent) {
        ProductEntity productEntity = productBo.getById(txtProductId.getText());
        txtName.setText(productEntity.getName());
        txtSize.setText(productEntity.getSize());
        txtPrice.setText(productEntity.getPrice()+"0");
        txtStock.setText(productEntity.getStock()+"");
    }

    public void btnReset(ActionEvent actionEvent) {

    }

    public void btnHold(ActionEvent actionEvent) {

    }

    ObservableList<CartTable> cartObList = FXCollections.observableArrayList();
    Integer count=0;

    public void btnAddToCart(ActionEvent actionEvent) {

        ProductEntity productEntity = productBo.getById(txtProductId.getText());
        Double discount = 0.0 ;
        if(productEntity.getStock()<Integer.parseInt(txtQuantity.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Enter Valid Quantity").show();
            txtQuantity.clear();
        }else{
            if(!txtDiscount.getText().equals("")){
                discount = Double.parseDouble(txtDiscount.getText());
            }
            Double total  = (productEntity.getPrice()*Integer.parseInt(txtQuantity.getText()))-(discount*Integer.parseInt(txtQuantity.getText()));

            CartTable cartObject = new CartTable(
                    count,
                    productEntity.getId(),
                    productEntity.getName(),
                    Integer.parseInt(txtQuantity.getText()),
                    discount,
                    productEntity.getPrice(),
                    total
            );
            cartObList.add(cartObject);
            tblOrder.setItems(cartObList);
            count++;
            RestTextFields();
            countTotals();
        }

    }

    private void countTotals() {
        Double subTotal= 0.0;
        Double discount =0.0;
        for(CartTable tableCart : cartObList){
            subTotal+=tableCart.getTotal();
            discount+=tableCart.getDiscount();
        }
        txtSubTotal.setText(subTotal+"0");
        txtDiscount1.setText(discount+"0");
        txtGrandTotal.setText((subTotal-discount)+"0");
    }

    private void RestTextFields() {
        txtName.clear();
        txtSize.clear();
        txtPrice.clear();
        txtStock.clear();
        txtDiscount.clear();
        txtQuantity.clear();
        txtProductId.clear();
    }

    ObservableList<RemoveTable>removeTable = FXCollections.observableArrayList();
    Integer countRemoveItem ;


    public void btnRemoveSearch(ActionEvent actionEvent) {

        String removeItemId = txtRemove.getText();
        cartObList.forEach(element ->{
            if(element.getItemNo().equals(removeItemId)){
                btnRemoveId.setDisable(false);
                countRemoveItem = element.getNumber();
                RemoveTable removeTable1 = new RemoveTable(
                        element.getNumber(),
                        element.getItemNo(),
                        element.getName(),
                        element.getQty(),
                        element.getUnitPrice(),
                        element.getTotal()
                );
                removeTable.add(removeTable1);
            }
        });
        tblRemove.setItems(removeTable);

    }

    public void btnRemove(ActionEvent actionEvent) {
        ObservableList<CartTable> temp = FXCollections.observableArrayList();
        cartObList.forEach(element ->{
            if(element.getNumber()!=countRemoveItem){
                temp.add(element);
            }
        });
        cartObList = temp;
        tblOrder.setItems(cartObList);
        resetCartRemove();
        countTotals();
    }

    private void resetCartRemove() {
        txtRemove.clear();
        removeTable.clear();
        btnRemoveId.setDisable(true);
    }

    public void RadioCashFunc(ActionEvent actionEvent) {
        if(RadioCash.isSelected()){
            if(!RadioCard.isSelected()){
                txtBalance.setText(Double.parseDouble(txtPaidAmount.getText())-Double.parseDouble(txtGrandTotal.getText())+"0");
            }
        }else{
            txtBalance.clear();
        }
    }

    public void RadioCardFunc(ActionEvent actionEvent) {
        if(RadioCard.isSelected()){
            if(!RadioCash.isSelected()){
                txtBalance.setText("Continue to Submit");
            }
        }else{
            txtBalance.clear();
        }

    }


    public void btnSubmit(ActionEvent actionEvent) {

    }



}
