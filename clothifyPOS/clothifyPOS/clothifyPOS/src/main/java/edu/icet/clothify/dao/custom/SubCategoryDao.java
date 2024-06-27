package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.SubCategoryEntity;

import java.util.List;

public interface SubCategoryDao extends CrudDao<SubCategoryEntity> {
    int empCount();

    String lastId();

    List<SubCategoryEntity> getAllSubCategoryDetail();

    SubCategoryEntity getById(String text);

    Boolean update(SubCategoryEntity entity);
}
