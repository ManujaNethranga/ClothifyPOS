package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.CategoryBo;
import edu.icet.clothify.bo.custom.SubCategoryBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.SubCategoryDao;
import edu.icet.clothify.dto.Category;
import edu.icet.clothify.dto.SubCategory;
import edu.icet.clothify.entity.CategoryEntity;
import edu.icet.clothify.entity.SubCategoryEntity;
import edu.icet.clothify.util.BoType;
import edu.icet.clothify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class SubCategoryBoImpl implements SubCategoryBo {

    SubCategoryDao subCategoryDao = DaoFactory.getInstance().getDao(DaoType.SUBCATEGORY);
    CategoryBo categoryBo = BoFactory.getInstance().getBo(BoType.CATEGORY);


    @Override
    public int empCount() {
        return subCategoryDao.empCount();
    }

    @Override
    public String lastEmpId() {
        return subCategoryDao.lastId();
    }

    @Override
    public Boolean save(SubCategory dto, String id) {
        Category byId = categoryBo.getById(id);
        SubCategoryEntity map = new ModelMapper().map(dto, SubCategoryEntity.class);
        map.setCategory(new ModelMapper().map(byId,CategoryEntity.class));
        return subCategoryDao.save(map);
    }

    @Override
    public ObservableList<SubCategoryEntity> getAllSubCategories() {
        ObservableList<SubCategoryEntity>obList = FXCollections.observableArrayList();
        List<SubCategoryEntity> list = subCategoryDao.getAllSubCategoryDetail();
        list.forEach(element ->{
            obList.add(element);
        });
        return obList;
    }

    @Override
    public SubCategoryEntity getById(String text) {
        return subCategoryDao.getById(text);
    }

    @Override
    public Boolean update(SubCategoryEntity entity) {
        return subCategoryDao.update(entity);
    }


}
