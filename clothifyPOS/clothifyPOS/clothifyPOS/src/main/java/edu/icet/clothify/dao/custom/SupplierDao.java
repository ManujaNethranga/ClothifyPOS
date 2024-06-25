package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.SupplierEntity;

import java.util.List;

public interface SupplierDao extends CrudDao<SupplierEntity> {
    int empCount();

    String getLastId();

    SupplierEntity getById(String text);

    Boolean update(SupplierEntity map);

    List<SupplierEntity> getAllSuppliers();
}
