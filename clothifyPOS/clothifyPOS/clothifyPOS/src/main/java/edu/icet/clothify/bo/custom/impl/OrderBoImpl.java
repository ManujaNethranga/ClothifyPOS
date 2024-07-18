package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.OrderBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.OrderDao;
import edu.icet.clothify.dto.Order;
import edu.icet.clothify.dto.User;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.entity.UserEntity;
import edu.icet.clothify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class OrderBoImpl implements OrderBo {

    OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public int empCount() {
        return orderDao.empCount();
    }

    @Override
    public String lastEmpId() {
        return orderDao.lastEmpId();
    }

    @Override
    public Boolean save(Order order, User user, List<OrderDetailsEntity> list) {
        OrderEntity map = new ModelMapper().map(order, OrderEntity.class);
        map.setList(list);
        map.setUserEntity(new ModelMapper().map(user, UserEntity.class));
        return orderDao.save(map);
    }

    @Override
    public OrderEntity getById(String orderId) {
        return orderDao.getById(orderId);
    }

    @Override
    public ObservableList<OrderEntity> getAllOrders() {
        List<OrderEntity> allOrders = orderDao.getAllOrders();
        ObservableList<OrderEntity>obList = FXCollections.observableArrayList();
        allOrders.forEach(element ->{
            obList.add(element);
        });
        return obList;
    }

    @Override
    public Boolean update(OrderEntity byId) {
        return orderDao.update(byId);
    }
}
