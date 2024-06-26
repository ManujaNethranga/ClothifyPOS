package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.CategoryBo;
import edu.icet.clothify.dto.Category;
import edu.icet.clothify.dto.tableModels.CategoryParentDetailTbl;
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
import java.rmi.AlreadyBoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryForm implements Initializable {
    public TextField txtgeneratedCategoryId;
    public TextField txtParentName;
    public TextField txtParentDescription;
    public TableView parentCategoryTable;
    public TableColumn colParentId;
    public TableColumn colParentName;
    public TableColumn colParentDescription;
    public TableColumn colParentRegDate;
    public TableColumn colParentIsActive;
    public TextField txtParentCategoryIDSearch;
    public TextField txtEditParentName;
    public TextField txtEditParentDescription;
    public ComboBox txtEditParentIsActive;
    public TextField txtSubGeneratedId;
    public TextField txtSubName;
    public TextField txtSubDescription;
    public TableView SubCategoryTable;
    public TableColumn colSubId;
    public TableColumn colSubSubId;
    public TableColumn colSubName;
    public TableColumn colSubDescription;
    public TableColumn colSubRegDate;
    public TableColumn colSubIsActive;
    public TextField txtSubCategoryIdSearch;
    public TextField txtSubEditName;
    public TextField txtSubEditDescription;
    public ComboBox cmbSubEditCategoryId;
    public ComboBox cmbSubCategoryId;
    public ComboBox cmbSubEditIsActive;
    public BorderPane CategoryBorderPane;
    public ComboBox cmbEditParentIsActive;

    CategoryBo categoryBo = BoFactory.getInstance().getBo(BoType.CATEGORY);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateParentCategoryId();
        loadParentCategoryTable();
        cmbEditParentIsActive.getItems().addAll("Active","Deactivated");
        colParentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colParentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colParentDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colParentRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colParentIsActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));
    }

    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/inventoryManagement.fxml")).load();
        CategoryBorderPane.getChildren().clear();
        CategoryBorderPane.setCenter(parent);
    }

    public void btnParentSave(ActionEvent actionEvent) {

        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = d.format(date);
        String catId=txtgeneratedCategoryId.getText();
        String name = txtParentName.getText();
        String desc = txtParentDescription.getText();

        Category category = new Category(
                catId,
                name,
                desc,
                dateString,
                true
        );
        if(name.equals("")&&desc.equals("")){
            new Alert(Alert.AlertType.INFORMATION,"Please Enter Date!!").show();
        }else{
            Boolean  isSaved = categoryBo.save(category);
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Category Saved Successfully!!").show();
                generateParentCategoryId();
                resetParentDetails();
            }
        }


    }

    private void resetParentDetails(){
        txtParentName.clear();
        txtParentDescription.clear();
    }

    public void btnEditSave(ActionEvent actionEvent) {
        Category categoryDetail = categoryBo.getById(txtParentCategoryIDSearch.getText());
        Boolean isActive = false;
        if(cmbEditParentIsActive.getValue().toString().equals("Active")){
            isActive = true;
        }
        Category category = new Category(
                categoryDetail.getId(),
                txtEditParentName.getText(),
                txtEditParentDescription.getText(),
                categoryDetail.getRegDate(),
                isActive

        );
        Boolean isUpdated = categoryBo.update(category);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Category Updated Sucessfully!!").show();
            resetParentEdit();
        }

    }

    private void resetParentEdit(){
        txtEditParentName.clear();
        txtEditParentDescription.clear();
        cmbEditParentIsActive.setValue("Availability");
        loadParentCategoryTable();
    }

    private void loadParentCategoryTable(){
        ObservableList<Category> obList = categoryBo.getAllCategories();
        ObservableList<CategoryParentDetailTbl> tbl = FXCollections.observableArrayList();
        obList.forEach(element ->{
            CategoryParentDetailTbl categoryParentDetailTbl = new CategoryParentDetailTbl(
                    element.getId(),
                    element.getName(),
                    element.getDescription(),
                    element.getRegDate(),
                    element.getIsActive()
            );
            tbl.add(categoryParentDetailTbl);
        });
        parentCategoryTable.setItems(tbl);
    }


    public void btnParentSearch(ActionEvent actionEvent) {
        Category category = categoryBo.getById(txtParentCategoryIDSearch.getText());
        txtEditParentName.setText(category.getName());
        txtEditParentDescription.setText(category.getDescription());
        if(category.getIsActive()){
            cmbEditParentIsActive.setValue("Active");
        }else{
            cmbEditParentIsActive.setValue("Deactivated");
        }
    }

    public void btnParentClear(ActionEvent actionEvent) {
        resetParentDetails();
    }

    public void txtParentSearchClear(ActionEvent actionEvent) {
        txtParentCategoryIDSearch.clear();
    }

    public void btnSubSave(ActionEvent actionEvent) {
    }

    public void btnSubEditSave(ActionEvent actionEvent) {
    }

    public void btnSubEditSearch(ActionEvent actionEvent) {
    }

    public void btnSubClear(ActionEvent actionEvent) {
    }

    public void btnSubEditClear(ActionEvent actionEvent) {
    }

    private void generateParentCategoryId(){
        int count = categoryBo.empCount();
        if (count==0){
            txtgeneratedCategoryId.setText("CAT001");
        }else{
            String lastEmployeeId = categoryBo.lastEmpId();
            Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
            Matcher matcher = pattern.matcher(lastEmployeeId);
            if(matcher.find()){
                int number = Integer.parseInt(matcher.group(1));
                number++;
                txtgeneratedCategoryId.setText(String.format("CAT%03d",number));
            }
        }
    }


}
