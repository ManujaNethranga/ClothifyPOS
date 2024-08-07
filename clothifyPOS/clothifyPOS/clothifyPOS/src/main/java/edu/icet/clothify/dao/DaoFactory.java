package edu.icet.clothify.dao;

import edu.icet.clothify.dao.custom.Impl.*;
import edu.icet.clothify.util.DaoType;

public class DaoFactory {
    private DaoFactory(){}

    private static  DaoFactory instance;

    public static DaoFactory getInstance(){
        return instance==null? instance = new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch(type){
            case EMPLOYEE: return (T) new EmployeeDaoImpl();
            case USER: return (T) new UserDaoImpl();
            case SUPPLIER: return (T) new SupplierDaoImpl();
            case CATEGORY: return (T) new CategoryDaoImpl();
            case SUBCATEGORY: return (T) new SubCategoryDaoImpl();
            case PRODUCT: return (T) new ProductDaoImpl();
            case STOCK: return  (T) new StockDaoImpl();
            case ORDER : return (T) new OrderDaoImpl();
            case ORDERDETAILS: return (T) new OrderDetailsDaoImpl();
        }
        return null;
    }
}
