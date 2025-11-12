package service.Impl;

import Repository.Impl.SupplierRepositoryImpl;
import Repository.SupplierRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Employee;
import model.dto.Supplier;
import service.SupplierService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository=new SupplierRepositoryImpl();

    @Override
    public ObservableList<Supplier> getAllDetails() {

        ObservableList<Supplier> supplierObservableList= FXCollections.observableArrayList();

        ResultSet allDetails = supplierRepository.getAllDetails();

        try {
            while(allDetails.next()){
                supplierObservableList.add(
                        new Supplier(
                                allDetails.getString("SupplierId"),
                                allDetails.getString("Name"),
                                allDetails.getString("Address"),
                                allDetails.getString("Email"),
                                allDetails.getString("PhoneNumber")
                        )
                );
            }

            return supplierObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
