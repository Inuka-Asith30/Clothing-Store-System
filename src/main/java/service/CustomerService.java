package service;

import javafx.collections.ObservableList;
import model.dto.Customer;


public interface CustomerService {
    ObservableList<Customer> getCustomerDetails();
    boolean addCustomerDetails(Customer customer);
    boolean deleteCustomerDetails(String customerId);
    boolean updateCustDetails(Customer customer);
    Customer searchCustomerbyId(String customerId);
    String getNewCustomerID();
    Customer searchCustomerbyPhoneNo(String phoneNumber);
}
