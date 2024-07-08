package edu.icet.clothify.controller;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.CategoryBo;
import edu.icet.clothify.bo.custom.ProductBo;
import edu.icet.clothify.bo.custom.SubCategoryBo;
import edu.icet.clothify.bo.custom.SupplierBo;
import edu.icet.clothify.dto.Category;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.dto.SubCategory;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.dto.tableModels.ProductDetailTable;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductPageForm implements Initializable {
    public BorderPane ProductBorderPane;
    public TextField txtProductId;
    public TextField txtProductName;
    public TextField txtDescription;
    public TextField txtPrice;
    public ComboBox cmbSize;
    public ComboBox cmbSupplierName;
    public TextField txtSupplierId;
    public ComboBox cmbSubCategory;
    public ImageView imgView;
    public TextField editTxtName;
    public TextField editProductId;
    public TextField editTxtDescription;
    public TextField editTxtPrice;
    public ComboBox editCmbSize;
    public ComboBox editCmbCategory;
    public ComboBox editCmbSupplierName;
    public TextField editSupplierId;
    public ComboBox editCmbSubCategory;
    public ImageView editImageView;
    public ComboBox editTxtIsActive;
    public ComboBox cmbCategory;
    public TableView productTable;
    public TableColumn colProductId;
    public TableColumn colName;
    public TableColumn colDescription;
    public TableColumn colSCategory;
    public TableColumn colSupplier;
    public TableColumn colSize;
    public TableColumn colisActive;
    public TableColumn colRegDate;
    public Button updateBtn;
    public TextField txtStock;
    public TableColumn colStock;

    CategoryBo categoryBo = BoFactory.getInstance().getBo(BoType.CATEGORY);
    SubCategoryBo subCategoryBo = BoFactory.getInstance().getBo(BoType.SUBCATEGORY);
    ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);
    FileChooser fileChooser = new FileChooser();
    File file;

    public void btnBack(MouseEvent mouseEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/inventoryManagement.fxml")).load();
        ProductBorderPane.getChildren().clear();
        ProductBorderPane.setCenter(parent);
    }

    public void btnSave(ActionEvent actionEvent) throws IOException, SQLException {
        Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = d.format(date);
        Product product = new Product();
        product.setId(txtProductId.getText());
        product.setStock(Integer.parseInt(txtStock.getText()));
        product.setName(txtProductName.getText());
        product.setPrice(Double.parseDouble(txtPrice.getText()));
        product.setDescription(txtDescription.getText());
        product.setSize(cmbSize.getValue().toString());
        product.setIsActive(true);
        product.setRegDate(dateString);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        inputStream.read(bytes);
        product.setImg(new SerialBlob(bytes));

       Boolean isSaved = productBo.save(product,cmbSubCategory.getValue().toString(),cmbSupplierName.getValue().toString());
       if(isSaved){
           new Alert(Alert.AlertType.INFORMATION,"Product Saves Successfully").show();
           resetTextFields();
           generateProductId();
           loadProductTable();
       }

    }

    private void resetTextFields(){
        txtProductId.clear();
        txtDescription.clear();
        txtPrice.clear();
        txtProductName.clear();
        cmbSize.setPromptText("Size");
        cmbCategory.setValue("Category");
        cmbSubCategory.setValue("Sub - Category");
        txtSupplierId.clear();
        cmbSupplierName.setValue("Supplier ID");
        imgView.setImage(null);

    }

    public void btnImageSelector(ActionEvent actionEvent) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        Stage stage = new Stage();
        file = fileChooser.showOpenDialog(stage);
        String imgPath = file.getAbsolutePath();
        imgView.setImage(new Image(imgPath));

    }
    File fileEdit ;
    FileChooser fileChooserNew = new FileChooser();

    public void editBtnUpdate(ActionEvent actionEvent) throws IOException, SQLException {
        ProductEntity productEntity = productBo.getById(editProductId.getText());
        ProductEntity product = new ProductEntity();
        product.setStock(productEntity.getStock());
        product.setRegDate(productEntity.getRegDate());
        product.setId(editProductId.getText());
        product.setName(editTxtName.getText());
        product.setDescription(editTxtDescription.getText());
        product.setPrice(Double.parseDouble(editTxtPrice.getText()));
        product.setSize(editCmbSize.getValue().toString());
        Boolean isActive = false;
        if(editTxtIsActive.getValue().toString().equals("Active")){
            isActive = true;
        }
        product.setIsActive(isActive);
        if(!(fileEdit ==null)){
            FileInputStream inputStream = new FileInputStream(fileEdit);
            byte[] bytes = new byte[(int)fileEdit.length()];
            inputStream.read(bytes);
            product.setImg(new SerialBlob(bytes));
        }else{
            product.setImg(productEntity.getImg());
        }
        System.out.println(product);
        Boolean isUpdated = productBo.update(product,editCmbSupplierName.getValue().toString(),editCmbSubCategory.getValue().toString());
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Product Details Successfully Updated!!").show();
            resetAllEdit();
            loadProductTable();
        }
    }

    private void resetAllEdit() {
        editCmbCategory.setValue(null);
        editCmbSubCategory.setValue(null);
        editCmbSupplierName.setValue(null);
        editCmbSize.setValue(null);
        editTxtIsActive.setValue(null);
        editCmbSize.setPromptText("Size");
        editCmbSubCategory.setPromptText("Sub - Category");
        editCmbCategory.setPromptText("Category");
        editCmbSupplierName.setPromptText("Supplier Name");
        editTxtIsActive.setPromptText("Availability");
        editSupplierId.clear();
        editTxtName.clear();
        editTxtDescription.clear();
        editTxtPrice.clear();
        editImageView.setImage(null);
        updateBtn.setDisable(true);


    }

    public void editbtnImageSelector(ActionEvent actionEvent) {
        fileChooserNew.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        Stage stage = new Stage();
        fileEdit = fileChooserNew.showOpenDialog(stage);
        String imgPath = fileEdit.getAbsolutePath();
        editImageView.setImage(new Image(imgPath));
    }

    public void editBtnSearch(ActionEvent actionEvent) throws SQLException {
        updateBtn.setDisable(false);
        editTxtIsActive.getItems().setAll("Active","Deactivate");
        ProductEntity productEntity = productBo.getById(editProductId.getText());
        if(productEntity.getIsActive()){
            editTxtIsActive.setValue("Active");
        }else{
            editTxtIsActive.setValue("Deactivated");
        }
        editProductId.setText(productEntity.getId());
        editTxtName.setText(productEntity.getName());
        editTxtDescription.setText(productEntity.getDescription());
        editTxtPrice.setText(productEntity.getPrice()+"");
        editCmbSize.setValue(productEntity.getSize());
        editCmbSupplierName.setValue(productEntity.getSupplierEntity().getId());
        editSupplierId.setText(productEntity.getSupplierEntity().getCompanyName());
        editCmbSubCategory.setValue(productEntity.getSubCategoryEntity().getId());
        editCmbCategory.setValue(productEntity.getSubCategoryEntity().getCategory().getId());
        InputStream inputStream = productEntity.getImg().getBinaryStream();
        Image image = new Image(inputStream);
        editImageView.setImage(image);
        editSubCategoryFuction();
        LoadEditCategory();
        ObservableList<Supplier> allSuppliers = supplierBo.getAllCustomers();
        ObservableList<String> suppliers = FXCollections.observableArrayList();
        allSuppliers.forEach(element ->{
            suppliers.add(element.getId());
        });
        editCmbSupplierName.setItems(suppliers);
        Supplier byId = supplierBo.getById(editCmbSupplierName.getValue().toString());
        editSupplierId.setText(byId.getCompanyName());
    }

    private void generateProductId(){
        int count = productBo.empCount();
        if (count==0){
            txtProductId.setText("PR001");
        }else{
            String lastEmployeeId = productBo.lastEmpId();
            Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
            Matcher matcher = pattern.matcher(lastEmployeeId);
            if(matcher.find()){
                int number = Integer.parseInt(matcher.group(1));
                number++;
                txtProductId.setText(String.format("PR%03d",number));
            }
        }
    }

    private void LoadCmbLists(){
        ObservableList<Category> allCategories = categoryBo.getAllCategories();
        ObservableList<String> categories = FXCollections.observableArrayList();
        allCategories.forEach(element ->{
            categories.add(element.getId());
        });
        ObservableList<Supplier> allSuppliers = supplierBo.getAllCustomers();
        ObservableList<String> suppliers = FXCollections.observableArrayList();
        allSuppliers.forEach(element ->{
            suppliers.add(element.getId());
        });
        cmbCategory.setItems(categories);

        cmbSupplierName.setItems(suppliers);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbCategory.setPromptText("Category");
        cmbSubCategory.setPromptText("Sub - Category");
        cmbSupplierName.setPromptText("Supplier ID");
        cmbSize.setPromptText("Size");
        editCmbSize.setPromptText("Size");
        editCmbSubCategory.setPromptText("Sub - Category");
        editCmbCategory.setPromptText("Category");
        editCmbSupplierName.setPromptText("Supplier Name");
        editTxtIsActive.setPromptText("Availability");
        generateProductId();
        LoadCmbLists();
        cmbSize.getItems().addAll("X SMALL","SMALL","MEDIUM","LARGE","X LARGE","2X LARGE","3X LARGE","4X LARGE");
        editCmbSize.getItems().addAll("X SMALL","SMALL","MEDIUM","LARGE","X LARGE","2X LARGE","3X LARGE","4X LARGE");
        loadProductTable();
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSCategory.setCellValueFactory(new PropertyValueFactory<>("sCategory"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colisActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }

    private void LoadEditCategory() {
        ObservableList<Category> allCategories = categoryBo.getAllCategories();
        ObservableList<String> categories = FXCollections.observableArrayList();
        allCategories.forEach(element ->{
            categories.add(element.getId());
        });
        editCmbCategory.setItems(categories);
    }

    public void categoryAction(ActionEvent actionEvent) {
        ObservableList<SubCategory> obList = subCategoryBo.getSubCategoryByParentCategory(cmbCategory.getValue().toString());
        ObservableList<String> list = FXCollections.observableArrayList();
        obList.forEach(element -> {
            list.add(element.getId());
        });
        cmbSubCategory.setItems(list);
    }

    public void supplierIIdAction(ActionEvent actionEvent) {
        Supplier byId = supplierBo.getById(cmbSupplierName.getValue().toString());
        txtSupplierId.setText(byId.getCompanyName());
    }

    private void loadProductTable(){
        ObservableList<ProductEntity> obList = productBo.getAllProducts();
        ObservableList<ProductDetailTable> table = FXCollections.observableArrayList();
        obList.forEach(element ->{
            ProductDetailTable productDetailTable = new ProductDetailTable(
                    element.getId(),
                    element.getName(),
                    element.getDescription(),
                    element.getSubCategoryEntity().getId(),
                    element.getSupplierEntity().getId(),
                    element.getSize(),
                    element.getIsActive(),
                    element.getRegDate(),
                    element.getStock()
            );
            table.add(productDetailTable);
        });
        productTable.setItems(table);
    }

    public void editCmbCategoryFunction(ActionEvent actionEvent) {
        editSubCategoryFuction();
    }

    public void editSubCategoryFuction(){
        ObservableList<SubCategory> obList = subCategoryBo.getSubCategoryByParentCategory(editCmbCategory.getValue().toString());
        ObservableList<String> list = FXCollections.observableArrayList();
        obList.forEach(element -> {
            list.add(element.getId());
        });
        editCmbSubCategory.setItems(list);
    }
}
