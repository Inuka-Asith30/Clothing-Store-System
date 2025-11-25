package service.Impl;

import Repository.CustomerRepository;
import Repository.Impl.CustomerRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import service.CustomerService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository =new CustomerRepositoryImpl();

    @Override
    public ObservableList<Customer> getCustomerDetails() {
        ResultSet resultSet = customerRepository.getCustDetails();
        ObservableList<Customer> customerObservableList= FXCollections.observableArrayList();


        try {
            while(resultSet.next()){
                customerObservableList.add(
                        new Customer(
                                resultSet.getString("CustID"),
                                resultSet.getString("Title"),
                                resultSet.getString("CustName"),
                                resultSet.getString("CustAddress"),
                                resultSet.getString("Email"),
                                resultSet.getString("PhoneNumber")
                        )
                );
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean addCustomerDetails(Customer customer) {
        return customerRepository.addCustDetails(customer);
    }

    @Override
    public boolean deleteCustomerDetails(String customerId) {
        return customerRepository.deleteCustDetails(customerId);
    }

    @Override
    public boolean updateCustDetails(Customer customer) {
        return customerRepository.updateCustDetails(customer);
    }

    @Override
    public Customer searchCustomerbyId(String customerId) {
        ResultSet resultSet = customerRepository.searchCustomerById(customerId);

        Customer customer=null;

        try {
            while(resultSet.next()){
                customer=new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("Title"),
                        resultSet.getString("CustName"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                );
            }

            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNewCustomerID() {
        ResultSet resultSet= customerRepository.getLastCustID();

        String lastId = null;

        try {
            while(resultSet.next()){
                lastId=resultSet.getString("CustID");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(lastId!=null){
            lastId = lastId.split("[A-Z]")[1];//C060-->060
            String newId= String.format("C%03d",(Integer.parseInt(lastId)+1));

            return newId;
        }

        return "C001";
    }

    @Override
    public Customer searchCustomerbyPhoneNo(String phoneNumber) {
        ResultSet resultSet = customerRepository.searchCustomerByPhoneNo(phoneNumber);

        Customer customer=null;

        try {
            while(resultSet.next()){
                customer=new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("Title"),
                        resultSet.getString("CustName"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                );
            }

            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCount() {

        ResultSet resultSet = customerRepository.getCount();

        int customersCount=0;

        try {
            while(resultSet.next()){
                customersCount = resultSet.getInt("count(*)");
            }

            return customersCount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
