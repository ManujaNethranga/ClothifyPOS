package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.CategoryBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.CategoryDao;
import edu.icet.clothify.dto.Category;
import edu.icet.clothify.entity.CategoryEntity;
import edu.icet.clothify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CategoryBoImpl implements CategoryBo {

    CategoryDao categoryDao = DaoFactory.getInstance().getDao(DaoType.CATEGORY);

    @Override
    public int empCount() {
        return categoryDao.empCount();
    }

    @Override
    public String lastEmpId() {
        return categoryDao.getLastId();
    }

    @Override
    public Boolean save(Category dto) {
        return categoryDao.save(new ModelMapper().map(dto, CategoryEntity.class));
    }

    @Override
    public Category getById(String categoryId) {
        return new ModelMapper().map(categoryDao.getById(categoryId),Category.class);
    }

    @Override
    public Boolean update(Category category) {
        return categoryDao.update(new ModelMapper().map(category, CategoryEntity.class));
    }

    @Override
    public ObservableList<Category> getAllCategories() {
        ObservableList<Category> obList = FXCollections.observableArrayList();
        List<CategoryEntity> list = categoryDao.getAllCategories();
        list.forEach(element ->{
            obList.add(new ModelMapper().map(element,Category.class));
        });
        return obList;
    }
}
