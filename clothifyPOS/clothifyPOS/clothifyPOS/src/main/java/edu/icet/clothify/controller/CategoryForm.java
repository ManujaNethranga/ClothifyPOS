package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.CategoryBo;
import edu.icet.clothify.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
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

    CategoryBo categoryBo = BoFactory.getInstance().getBo(BoType.CATEGORY);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateSupplierId();
    }

    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/inventoryManagement.fxml")).load();
        CategoryBorderPane.getChildren().clear();
        CategoryBorderPane.setCenter(parent);
    }

    public void btnParentSave(ActionEvent actionEvent) {
    }

    public void btnEditSave(ActionEvent actionEvent) {
    }

    public void btnParentSearch(ActionEvent actionEvent) {
    }

    public void btnParentClear(ActionEvent actionEvent) {
    }

    public void txtParentSearchClear(ActionEvent actionEvent) {
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

    private void generateSupplierId(){
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
                txtgeneratedCategoryId.setText(String.format("SUP%03d",number));
            }
        }
    }


}
