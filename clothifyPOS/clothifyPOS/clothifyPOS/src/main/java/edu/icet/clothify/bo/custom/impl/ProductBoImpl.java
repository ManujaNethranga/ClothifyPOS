package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.ProductBo;
import edu.icet.clothify.bo.custom.SubCategoryBo;
import edu.icet.clothify.bo.custom.SupplierBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.ProductDao;
import edu.icet.clothify.dao.custom.SubCategoryDao;
import edu.icet.clothify.dao.custom.SupplierDao;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductBoImpl implements ProductBo {

    ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
    SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
    SubCategoryDao categoryDao = DaoFactory.getInstance().getDao(DaoType.SUBCATEGORY);


    @Override
    public int empCount() {
        return productDao.empCount();
    }

    @Override
    public String lastEmpId() {
        return productDao.lastEmpId();
    }

    @Override
    public Boolean save(Product product, String subCategory, String supplier) {
        ProductEntity map = new ModelMapper().map(product, ProductEntity.class);
        map.setSubCategoryEntity(categoryDao.getById(subCategory));
        map.setSupplierEntity(new ModelMapper().map(supplierDao.getById(supplier),SupplierEntity.class));
        return productDao.save(map);
    }

    @Override
    public ObservableList<ProductEntity> getAllProducts() {
        List<ProductEntity> list = productDao.getAllProducts();
        ObservableList<ProductEntity> obList = FXCollections.observableArrayList();
        list.forEach(element ->{
            obList.add(element);
        });
        return obList;
    }

    @Override
    public ProductEntity getById(String id) {
        return productDao.getById(id);
    }

    @Override
    public Boolean update(ProductEntity product, String supllier ,String subCategory) {
        product.setSupplierEntity(new ModelMapper().map(supplierDao.getById(supllier),SupplierEntity.class));
        product.setSubCategoryEntity(categoryDao.getById(subCategory));
        return productDao.update(product);
    }

    @Override
    public Boolean updateEntity(ProductEntity byId) {
        return productDao.update(byId);
    }
}
