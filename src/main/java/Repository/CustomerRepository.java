package Repository;

import model.dto.Customer;

import java.sql.ResultSet;

public interface CustomerRepository {
    ResultSet getCustDetails();
    boolean addCustDetails(Customer Customer);
    boolean deleteCustDetails(String CustomerId);
    boolean updateCustDetails(Customer Customer);
    ResultSet searchCustomerById(String CustId);
    ResultSet getLastCustID();
    ResultSet searchCustomerByPhoneNo(String phoneNumber);
    ResultSet getCount();
}
