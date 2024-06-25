package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.SupplierBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.SupplierDao;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class SupplierBoImpl implements SupplierBo {

    SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
    @Override
    public int empCount() {
        return supplierDao.empCount();
    }

    @Override
    public String lastEmpId() {
        return supplierDao.getLastId();
    }

    @Override
    public Boolean save(Supplier dto) {
        return supplierDao.save(new ModelMapper().map(dto,SupplierEntity.class));
    }

    @Override
    public Supplier getById(String id) {
        return new ModelMapper().map(supplierDao.getById(id),Supplier.class);
    }

    @Override
    public Boolean update(Supplier supplierDto) {
        return supplierDao.update(new ModelMapper().map(supplierDto, SupplierEntity.class));
    }

    @Override
    public ObservableList<Supplier> getAllCustomers() {
        ObservableList<Supplier>supList = FXCollections.observableArrayList();
        List<SupplierEntity> list = supplierDao.getAllSuppliers();
        list.forEach(element ->{
            supList.add(new ModelMapper().map(element,Supplier.class));
        });
        return supList;
    }
}
