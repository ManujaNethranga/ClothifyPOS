package edu.icet.clothify.bo;

import edu.icet.clothify.bo.custom.impl.EmployeeBoImpl;
import edu.icet.clothify.bo.custom.impl.UserBoImpl;
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
        }
        return null;
    }

}
