package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.CategoryBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.CategoryDao;
import edu.icet.clothify.util.DaoType;

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
}
