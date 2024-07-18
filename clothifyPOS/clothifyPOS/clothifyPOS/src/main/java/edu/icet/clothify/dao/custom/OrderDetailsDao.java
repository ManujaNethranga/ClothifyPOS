package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.OrderDetailsEntity;

import java.util.List;

public interface OrderDetailsDao extends CrudDao<OrderDetailsEntity> {
    List<OrderDetailsEntity> getAllDetails();
}
