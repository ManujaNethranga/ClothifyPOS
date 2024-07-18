package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.Order;
import edu.icet.clothify.dto.User;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.entity.OrderEntity;
import javafx.collections.ObservableList;

import java.util.List;

public interface OrderBo extends SuperBo {
    int empCount();

    String lastEmpId();

    Boolean save(Order order, User user, List<OrderDetailsEntity> list);

    OrderEntity getById(String orderId);

    ObservableList<OrderEntity> getAllOrders();

    Boolean update(OrderEntity byId);
}
