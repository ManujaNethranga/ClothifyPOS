package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.OrderDetails;

public interface OrderDetailsBo extends SuperBo {
    void save(OrderDetails orderDetails);
}
