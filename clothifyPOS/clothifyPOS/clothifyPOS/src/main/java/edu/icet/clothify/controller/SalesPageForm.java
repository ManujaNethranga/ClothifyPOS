package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.OrderBo;
import edu.icet.clothify.bo.custom.OrderDetailsBo;
import edu.icet.clothify.bo.custom.ProductBo;
import edu.icet.clothify.dto.tableModels.AllOrderTable;
import edu.icet.clothify.dto.tableModels.OrderDetailsTable;
import edu.icet.clothify.dto.tableModels.OrderTable;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.entity.OrderEntity;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SalesPageForm implements Initializable {
    public BorderPane SalesPane;
    public TextField txtOrderId;
    public TableView tblOrder;
    public TableColumn orColId;
    public TableColumn orColUserName;
    public TableColumn OrColPaymentType;
    public TableColumn orColDate;
    public TableColumn orColisReturned;
    public TableColumn orColEmail;
    public javafx.scene.control.DatePicker DatePicker;
    public TableView tblOrderDetails;
    public TableColumn deColOrderId;
    public TableColumn deColItemId;
    public TableColumn deColItemName;
    public TableColumn deColPrice;
    public TableColumn deColQuantity;
    public TableColumn deColDiscount;
    public TableColumn deColTotal;
    public TableView tableAllDetails;
    public TableColumn allColOrderId;
    public TableColumn allColUserName;
    public TableColumn allColPaymentType;
    public TableColumn allColDate;
    public TableColumn allColisReturned;
    public TableColumn allColEmail;
    public ComboBox cmbFilter;
    public TextField txtReturnOrderId;

    ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);
    OrderDetailsBo orderDetailsBo = BoFactory.getInstance().getBo(BoType.ORDERDETAILS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderTableDefault();
        orColId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orColUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        OrColPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        orColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        orColisReturned.setCellValueFactory(new PropertyValueFactory<>("isReturned"));
        orColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ////////
        deColOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        deColItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        deColItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        deColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        deColQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        deColDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        deColTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        ////////
        allColOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        allColUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        allColPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        allColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        allColisReturned.setCellValueFactory(new PropertyValueFactory<>("isReturned"));
        allColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadAllOrderDetails();
    }



    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/AdminDashboardPage.fxml")).load();
        SalesPane.getChildren().clear();
        SalesPane.setCenter(parent);
    }

    public void ReturnIdKeyFunction(KeyEvent keyEvent) {

    }

    public void DatePickerAction(ActionEvent actionEvent) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(DatePicker.getValue().toString());
        String dateToday = format.format(date);
        ObservableList<OrderEntity> allOrders = orderBo.getAllOrders();
        ObservableList<OrderTable> ordertbl = FXCollections.observableArrayList();
        allOrders.forEach(element ->{
            if(dateToday.equals(element.getDate())){
                OrderTable orderTable = new OrderTable();
                orderTable.setOrderId(element.getId());
                orderTable.setUserName(element.getName());
                orderTable.setPaymentType(element.getPaymentType());
                orderTable.setDate(element.getDate());
                orderTable.setIsReturned(element.getIsReturned());
                orderTable.setEmail(element.getEmail());
                ordertbl.add(orderTable);
            }
        });
        tblOrder.setItems(ordertbl);

    }

    public void cmbFilterAction(ActionEvent actionEvent) {
    }

    public void btnSubmit(ActionEvent actionEvent) {
        String orderId = txtReturnOrderId.getText();
        OrderEntity byId = orderBo.getById(orderId);
        byId.setIsReturned(true);
        Boolean isUpdated = orderBo.update(byId);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Order Returned Successfully!!").show();
            txtReturnOrderId.clear();
        }
        loadAllOrderDetails();
    }

    public void OrderIdKeyFunction(KeyEvent keyEvent) {
        String orderId = txtOrderId.getText();
        OrderEntity byId = orderBo.getById(orderId);
        if(byId==null){
            loadOrderTableDefault();
            tblOrderDetails.setItems(null);
        }else{
            loadSearchOrderId(byId);
            loadSearchOrderIdDetails(byId);
        }
    }

    private void loadSearchOrderIdDetails(OrderEntity byId) {
        ObservableList<OrderDetailsTable> table = FXCollections.observableArrayList();

        List<OrderDetailsEntity> list = orderDetailsBo.getAllOrderDetails();

        list.forEach(element ->{
            if(element.getOrderIdVal().equals(byId.getId())){
                OrderDetailsTable orderDetailsTable = new OrderDetailsTable();
                ProductEntity itemDetail = productBo.getById(element.getItemNo());
                orderDetailsTable.setOrderId(element.getOrderIdVal());
                orderDetailsTable.setItemId(element.getItemNo());
                orderDetailsTable.setName(itemDetail.getName());
                orderDetailsTable.setPrice(itemDetail.getPrice());
                orderDetailsTable.setQuantity(element.getQuantity());
                //orderDetailsTable.setDiscount();
                orderDetailsTable.setTotal(element.getQuantity()* itemDetail.getPrice());
                table.add(orderDetailsTable);
            }

        });
        tblOrderDetails.setItems(table);
    }


    private void loadSearchOrderId(OrderEntity entity) {
        OrderTable orderTable = new OrderTable();
        orderTable.setOrderId(entity.getId());
        orderTable.setUserName(entity.getName());
        orderTable.setPaymentType(entity.getPaymentType());
        orderTable.setDate(entity.getDate());
        orderTable.setIsReturned(entity.getIsReturned());
        orderTable.setEmail(entity.getEmail());
        if(!entity.getIsReturned()){
            ObservableList<OrderTable> obList = FXCollections.observableArrayList();
            obList.add(orderTable);
            tblOrder.setItems(obList);
        }else{
            txtOrderId.clear();
            txtOrderId.setPromptText("Order Already Returned !!");
            loadOrderTableDefault();
        }

    }

    private void loadOrderTableDefault() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateToday = f.format(date);
        ObservableList<OrderTable> obList = FXCollections.observableArrayList();
        ObservableList<OrderEntity> list = orderBo.getAllOrders();
        list.forEach(element ->{
            if(element.getDate().equals(dateToday)){
                OrderTable orderTable = new OrderTable();
                orderTable.setOrderId(element.getId());
                orderTable.setUserName(element.getName());
                orderTable.setPaymentType(element.getPaymentType());
                orderTable.setDate(element.getDate());
                orderTable.setIsReturned(element.getIsReturned());
                orderTable.setEmail(element.getEmail());
                obList.add(orderTable);
            }
        });
        tblOrder.setItems(obList);

    }

    private void loadAllOrderDetails() {
        ObservableList<AllOrderTable> table = FXCollections.observableArrayList();
        ObservableList<OrderEntity> allOrders = orderBo.getAllOrders();
        allOrders.forEach(element ->{
            AllOrderTable orderTable = new AllOrderTable();
            orderTable.setOrderId(element.getId());
            orderTable.setUsername(element.getName());
            orderTable.setPaymentType(element.getPaymentType());
            orderTable.setDate(element.getDate());
            orderTable.setIsReturned(element.getIsReturned());
            orderTable.setEmail(element.getEmail());
            table.add(orderTable);
        });
        tableAllDetails.setItems(table);
    }



}