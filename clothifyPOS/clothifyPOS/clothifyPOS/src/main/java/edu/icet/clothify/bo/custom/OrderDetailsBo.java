package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.OrderDetails;
import edu.icet.clothify.entity.OrderDetailsEntity;

import java.util.List;

public interface OrderDetailsBo extends SuperBo {
    void save(OrderDetails orderDetails);

    List<OrderDetailsEntity> getAllOrderDetails();
}
