package service;

import javafx.collections.ObservableList;
import model.dto.SystemAccess;

public interface SystemAccessService {
    public ObservableList<SystemAccess> getAllDetails();
    boolean addDetails(SystemAccess systemAccess);
    boolean deleteDetails(String empId);
    boolean updateDetails(SystemAccess systemAccess);
    SystemAccess searchEmployee(String empId);
}
