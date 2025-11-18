package service;

import javafx.collections.ObservableList;
import model.dto.SystemAccess;

public interface SystemAccessService {
    public ObservableList<SystemAccess> getAllDetails();
    SystemAccess searchEmployee(String empId);
}
