package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.dto.tableModels.CartTable;
import edu.icet.clothify.entity.ProductEntity;
import javafx.collections.ObservableList;

public interface ProductBo extends SuperBo {
    int empCount();

    String lastEmpId();

    Boolean save(Product product, String string, String string1);

    ObservableList<ProductEntity> getAllProducts();

    ProductEntity getById(String text);

    Boolean update(ProductEntity product, String string, String string1);

    Boolean updateEntity(ProductEntity byId);

    ObservableList<Product> getLowToHigh();

    ObservableList<Product> getHighToLow();

    Boolean updateStock(ObservableList<CartTable> cartObList);
}
