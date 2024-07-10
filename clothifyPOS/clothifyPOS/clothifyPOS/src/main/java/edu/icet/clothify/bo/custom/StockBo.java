package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.StockUpdateDetails;
import edu.icet.clothify.entity.StockUpdateDetailEntity;
import javafx.collections.ObservableList;

public interface StockBo extends SuperBo {
    Boolean save(StockUpdateDetails stock);

    ObservableList<StockUpdateDetails> getAllStockDetails();
}
