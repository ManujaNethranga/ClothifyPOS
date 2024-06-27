package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.Category;
import edu.icet.clothify.dto.SubCategory;
import edu.icet.clothify.entity.SubCategoryEntity;
import javafx.collections.ObservableList;

public interface SubCategoryBo extends SuperBo {
    int empCount();

    String lastEmpId();

    Boolean save(SubCategory dto, String id);

    ObservableList<SubCategoryEntity> getAllSubCategories();

    SubCategoryEntity getById(String text);

    Boolean update(SubCategoryEntity entity);
}
