package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {

    int empCount();

    String getLastEmployeeId();

    EmployeeEntity getById(String id);

    Boolean updateEmployee(EmployeeEntity map);

    List<EmployeeEntity> getAllEmployeeDetails();
}
