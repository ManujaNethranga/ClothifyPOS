package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.CategoryEntity;

import java.util.List;

public interface CategoryDao extends CrudDao<CategoryEntity> {
    int empCount();

    String getLastId();

    Object getById(String id);

    Boolean update(CategoryEntity map);

    List<CategoryEntity> getAllCategories();
}
