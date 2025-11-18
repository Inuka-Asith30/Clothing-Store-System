package service.Impl;

import Repository.Impl.SystemAccessRepositoryImpl;
import Repository.SystemAccessRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.SystemAccess;
import service.SystemAccessService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAccessServiceImpl implements SystemAccessService {

    SystemAccessRepository systemAccessRepository=new SystemAccessRepositoryImpl();

    @Override
    public ObservableList<SystemAccess> getAllDetails() {

        ObservableList<SystemAccess> systemAccessObservableList= FXCollections.observableArrayList();

        ResultSet resultSet = systemAccessRepository.getAllDetails();

        try {
            while(resultSet.next()){
                systemAccessObservableList.add(
                        new SystemAccess(
                                resultSet.getString("EmpID"),
                                resultSet.getString("Password")
                ));
            }

            return systemAccessObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SystemAccess searchEmployee(String empId) {

        ResultSet resultSet = systemAccessRepository.searchEmployee(empId);

        SystemAccess systemAccess=null;

        try {
            while(resultSet.next()){
                systemAccess=new SystemAccess(resultSet.getString("EmpID"),resultSet.getString("Password"));
            }

            return systemAccess;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
