package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.EmployeeBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.EmployeeDao;
import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {

    private final EmployeeDao employeeDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);

    @Override
    public boolean saveEmployee(Employee dto) {
        EmployeeEntity employee = new ModelMapper().map(dto, EmployeeEntity.class);
        return employeeDao.save(employee);
    }

    @Override
    public int empCount() {
        return employeeDao.empCount();
    }

    @Override
    public String lastEmpId() {
        return employeeDao.getLastEmployeeId();
    }

    @Override
    public Employee getById(String id) {
        return new ModelMapper().map(employeeDao.getById(id),Employee.class);
    }

    @Override
    public Boolean updateEmployee(Employee dto) {
        return employeeDao.updateEmployee(new ModelMapper().map(dto,EmployeeEntity.class));
    }

    @Override
    public ObservableList<Employee> getAllEmployeeDetails() {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        List<EmployeeEntity> allEmployees = employeeDao.getAllEmployeeDetails();
        allEmployees.forEach(element ->{
            list.add(new ModelMapper().map(element,Employee.class));
        });
        return list;
    }
}
