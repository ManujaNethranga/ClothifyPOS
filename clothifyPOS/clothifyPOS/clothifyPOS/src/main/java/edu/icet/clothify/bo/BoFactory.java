package edu.icet.clothify.bo;

import edu.icet.clothify.bo.custom.impl.*;
import edu.icet.clothify.util.BoType;

public class BoFactory {
    private BoFactory(){}

    private static BoFactory instance;

    public static BoFactory getInstance(){
        return instance==null?instance=new BoFactory():instance;
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case EMPLOYEE: return (T) new EmployeeBoImpl();
            case USER: return (T) new UserBoImpl();
            case SUPPLIER: return (T) new SupplierBoImpl();
            case CATEGORY: return (T) new CategoryBoImpl();
            case SUBCATEGORY: return (T) new SubCategoryBoImpl();
            case PRODUCT: return (T) new ProductBoImpl();
            case STOCK: return (T) new StockBoImpl();
            case ORDER : return (T) new OrderBoImpl();
            case ORDERDETAILS : return (T) new OrderDetailsBoImpl();
        }
        return null;
    }

}
