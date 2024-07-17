package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.OrderEntity;

public interface OrderDao extends CrudDao<OrderEntity> {
    int empCount();

    String lastEmpId();
}
