package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.dto.tableModels.CartTable;
import edu.icet.clothify.entity.ProductEntity;
import javafx.collections.ObservableList;

import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity> {
    int empCount();

    String lastEmpId();

    List<ProductEntity> getAllProducts();

    ProductEntity getById(String id);

    Boolean update(ProductEntity productEntity);

    List<ProductEntity> getLowToHigh();

    List<ProductEntity> getHighToLow();


    void updateStock(ProductEntity product);
}
