package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.Category;
import javafx.collections.ObservableList;

public interface CategoryBo extends SuperBo {
    int empCount();

    String lastEmpId();

    Boolean save(Category dto);

    Category getById(String categoryId);

    Boolean update(Category category);

    ObservableList<Category> getAllCategories();
}
