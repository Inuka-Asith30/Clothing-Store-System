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

    @Override
    public boolean addSupplierDetails(Supplier supplier) {
        return supplierRepository.addDetails(supplier);
    }

    @Override
    public boolean updateSupplierDetails(Supplier supplier) {
        return supplierRepository.updateDetails(supplier);
    }

    @Override
    public boolean deleteSupplierDetails(String supplierId) {
        return supplierRepository.deleteDetails(supplierId);
    }

    @Override
    public String getNewSupplierID() {
        ResultSet resultSet = supplierRepository.getLastId();

        String lastId=null;

        try {
            while(resultSet.next()){
                lastId = resultSet.getString("SupplierId");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(lastId!=null){
            lastId = lastId.split("[A-Z]")[1];
            String newId=String.format("S%03d",Integer.parseInt(lastId)+1);
            return newId;
        }
        return "S001";
    }

    @Override
    public Supplier searchSupplier(String supplierId) {
        ResultSet resultSet = supplierRepository.searchDetails(supplierId);

        Supplier supplier=null;

        try {
            while(resultSet.next()){
                supplier=new Supplier(
                        resultSet.getString("SupplierId"),
                        resultSet.getString("Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                );
            }

            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
