package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.SupplierBo;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.dto.tableModels.SupplyDetailTable;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierForm implements Initializable {
    public BorderPane SupplierPane;
    public TextField txtId;
    public TextField txtCompanyName;
    public TextField txtTitle;
    public TextField txtEmail;
    public TextField txtContact;
    public TextField txtContact2;
    public TextField txtAddress;
    public TextField UpdateId;
    public TextField updateComapanyName;
    public TextField UpdateDescription;
    public TextField UpdateEmail;
    public TextField UpdateLandNumber;
    public TextField UpdateAddress;
    public ComboBox UpdateAvailabityCmb;
    public TableView SupplierTable;
    public TableColumn colSupplierId;
    public TableColumn colEmail;
    public TableColumn colCompanyName;
    public TableColumn colTitle;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colRegisteredDate;
    public TableColumn colIsActive;

    SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateSupplierId();
        UpdateAvailabityCmb.getItems().addAll("Active","Deactivate");
        LoadSupplierDetails();
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colRegisteredDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colIsActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));

    }

    public void btnSave(ActionEvent actionEvent) throws IOException {
        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = d.format(date);
        Supplier supplier = new Supplier(
                txtId.getText(),
                txtTitle.getText(),
                txtEmail.getText(),
                txtCompanyName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                dateString,
                true
        );
        Boolean isSaved = supplierBo.save(supplier);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Saved Successfully!!").show();
            reset();
            generateSupplierId();
            LoadSupplierDetails();
        }
    }

    private void reset(){
        txtId.clear();
        txtId.clear();
        txtTitle.clear();
        txtEmail.clear();
        txtCompanyName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtContact2.clear();
    }

    private void resetUpdate(){
        UpdateId.clear();
        UpdateDescription.clear();
        UpdateEmail.clear();
        updateComapanyName.clear();
        UpdateAddress.clear();
        UpdateLandNumber.clear();
        UpdateAvailabityCmb.setValue("");
    }

    private void generateSupplierId(){
        int count = supplierBo.empCount();
        if (count==0){
            txtId.setText("SUP001");
        }else{
            String lastEmployeeId = supplierBo.lastEmpId();
            Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
            Matcher matcher = pattern.matcher(lastEmployeeId);
            if(matcher.find()){
                int number = Integer.parseInt(matcher.group(1));
                number++;
                txtId.setText(String.format("SUP%03d",number));
            }
        }
    }


    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/inventoryManagement.fxml")).load();
        SupplierPane.getChildren().clear();
        SupplierPane.setCenter(parent);
    }



    public void btnSearch(ActionEvent actionEvent) {
        Supplier supplier = supplierBo.getById(UpdateId.getText());

        updateComapanyName.setText(supplier.getCompanyName());
        if(supplier.getIsActive()){
            UpdateAvailabityCmb.setValue("Active");
        }else{
            UpdateAvailabityCmb.setValue("Deactivate");
        }
        UpdateAddress.setText(supplier.getAddress());
        UpdateDescription.setText(supplier.getTitle());
        UpdateEmail.setText(supplier.getEmail());
        UpdateLandNumber.setText(supplier.getContact());
    }

    public void btnUpdate(ActionEvent actionEvent) {
        Supplier supplier = supplierBo.getById(UpdateId.getText());
        Boolean isActive =false;
        if(UpdateAvailabityCmb.getValue().toString().equals("Active")){
            isActive = true;
        }
        Supplier supplierDto = new Supplier(
                UpdateId.getText(),
                UpdateDescription.getText(),
                UpdateEmail.getText(),
                updateComapanyName.getText(),
                UpdateAddress.getText(),
                UpdateLandNumber.getText(),
                supplier.getRegDate(),
                isActive
        );
        Boolean isUpdated = supplierBo.update(supplierDto);
        if(isUpdated){
            LoadSupplierDetails();
            resetUpdate();
            new Alert(Alert.AlertType.INFORMATION,"Supplier Updated Sucessfully").show();
        }
    }

    private void LoadSupplierDetails(){
        ObservableList<Supplier>suppliers = supplierBo.getAllCustomers();
        ObservableList<SupplyDetailTable> tblList = FXCollections.observableArrayList();
        suppliers.forEach(element ->{
            SupplyDetailTable table = new SupplyDetailTable(
                    element.getId(),
                    element.getAddress(),
                    element.getCompanyName(),
                    element.getContact(),
                    element.getEmail(),
                    element.getIsActive(),
                    element.getRegDate(),
                    element.getTitle()
            );
            tblList.add(table);
        });
        SupplierTable.setItems(tblList);
    }
}
