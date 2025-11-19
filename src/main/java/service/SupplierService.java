package service;

import javafx.collections.ObservableList;
import model.dto.Supplier;

public interface SupplierService {

    ObservableList<Supplier> getAllDetails();
    boolean addSupplierDetails(Supplier supplier);
    boolean updateSupplierDetails(Supplier supplier);
    boolean deleteSupplierDetails(String supplierId);
    String getNewSupplierID();
    Supplier searchSupplier(String supplierId);


}
