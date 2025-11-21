package Repository.Impl;

import DB.DBConnection;
import Repository.CustomerRepository;
import model.dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public ResultSet getCustDetails() {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from customer");
            ResultSet resultSet=preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCustDetails(Customer Customer) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO customer(CustID,Title,CustName,CustAddress,Email,PhoneNumber) VALUES(?,?,?,?,?,?)");
            preparedStatement.setObject(1,Customer.getCustomerId());
            preparedStatement.setObject(2,Customer.getTitle());
            preparedStatement.setObject(3,Customer.getName());
            preparedStatement.setObject(4,Customer.getAddress());
            preparedStatement.setObject(5,Customer.getEmail());
            preparedStatement.setObject(6,Customer.getPhoneNumber());

            return preparedStatement.executeUpdate() > 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustDetails(String customerId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("delete from customer where CustID=?");
            preparedStatement.setObject(1,customerId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustDetails(Customer customer) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE customer SET Title=?,CustName=?,CustAddress=?,Email=?,PhoneNumber=? WHERE CustID=?");

            preparedStatement.setObject(1,customer.getTitle());
            preparedStatement.setObject(2,customer.getName());
            preparedStatement.setObject(3,customer.getAddress());
            preparedStatement.setObject(4,customer.getEmail());
            preparedStatement.setObject(5,customer.getPhoneNumber());
            preparedStatement.setObject(6,customer.getCustomerId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet searchCustomerById(String CustId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from customer where CustID=?");
            preparedStatement.setObject(1,CustId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getLastCustID() {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select CustID from customer Order by CustID DESC LImit 1");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchCustomerByPhoneNo(String phoneNumber) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from customer where PhoneNumber=?");
            preparedStatement.setObject(1,phoneNumber);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
