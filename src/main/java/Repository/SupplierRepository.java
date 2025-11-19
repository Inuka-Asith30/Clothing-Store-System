package Repository;

import model.dto.Employee;
import model.dto.Supplier;

import java.sql.ResultSet;

public interface SupplierRepository {
    ResultSet getAllDetails();
    boolean addDetails(Supplier supplier);
    boolean updateDetails(Supplier supplier);
    boolean deleteDetails(String supplierId);
    ResultSet getLastId();
    ResultSet searchDetails(String supplierId);
}
