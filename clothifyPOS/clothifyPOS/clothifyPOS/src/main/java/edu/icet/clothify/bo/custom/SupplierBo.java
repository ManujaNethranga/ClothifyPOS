package edu.icet.clothify.bo.custom;

import edu.icet.clothify.bo.SuperBo;
import edu.icet.clothify.dto.Supplier;
import javafx.collections.ObservableList;

public interface SupplierBo extends SuperBo {
    int empCount();

    String lastEmpId();

    Boolean save(Supplier dto);

    Supplier getById(String text);

    Boolean update(Supplier supplierDto);

    ObservableList<Supplier> getAllCustomers();
}
