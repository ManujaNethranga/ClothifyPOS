package edu.icet.clothify.dao.custom;

import edu.icet.clothify.dao.CrudDao;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.entity.StockUpdateDetailEntity;

import java.util.List;

public interface StockDao extends CrudDao<StockUpdateDetailEntity> {
    List<StockUpdateDetailEntity> getAllStockDetails();
}
