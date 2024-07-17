package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.OrderDetailsBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.OrderDetailsDao;
import edu.icet.clothify.dto.OrderDetails;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.util.DaoType;
import org.modelmapper.ModelMapper;

public class OrderDetailsBoImpl implements OrderDetailsBo {

    OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.ORDERDETAILS);

    @Override
    public void save(OrderDetails orderDetails) {
        orderDetailsDao.save(new ModelMapper().map(orderDetails, OrderDetailsEntity.class));
    }
}
