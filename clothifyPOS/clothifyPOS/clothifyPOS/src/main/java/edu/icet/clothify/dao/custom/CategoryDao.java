package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.CategoryEntity;

public interface CategoryDao extends CrudDao<CategoryEntity> {
    int empCount();

    String getLastId();
}
