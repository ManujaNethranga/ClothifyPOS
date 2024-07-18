package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.OrderEntity;
import javafx.collections.ObservableList;

import java.util.List;

public interface OrderDao extends CrudDao<OrderEntity> {
    int empCount();

    String lastEmpId();

    OrderEntity getById(String orderId);

    List<OrderEntity> getAllOrders();

    Boolean update(OrderEntity byId);
}
