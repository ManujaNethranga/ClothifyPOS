package edu.icet.clothify.bo.custom.impl;

import edu.icet.clothify.bo.custom.StockBo;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.StockDao;
import edu.icet.clothify.dto.StockUpdateDetails;
import edu.icet.clothify.entity.StockUpdateDetailEntity;
import edu.icet.clothify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.util.List;

public class StockBoImpl implements StockBo {

    StockDao stockDao = DaoFactory.getInstance().getDao(DaoType.STOCK);

    @Override
    public Boolean save(StockUpdateDetails stock) {
        return stockDao.save(new ModelMapper().map(stock,StockUpdateDetailEntity.class));
    }

    @Override
    public ObservableList<StockUpdateDetails> getAllStockDetails() {
        List<StockUpdateDetailEntity> list = stockDao.getAllStockDetails();
        ObservableList<StockUpdateDetails> obList = FXCollections.observableArrayList();
        list.forEach(element ->{
            StockUpdateDetails stockUpdateDetails = new StockUpdateDetails(
                    element.getId(),
                    element.getProductId(),
                    element.getName(),
                    element.getPrice(),
                    element.getQuantity(),
                    element.getDate()
            );
            obList.add(stockUpdateDetails);
        });
        return obList;
    }
}
