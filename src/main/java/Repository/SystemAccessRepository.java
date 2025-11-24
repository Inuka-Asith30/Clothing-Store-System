package Repository;

import model.dto.SystemAccess;

import java.sql.ResultSet;

public interface SystemAccessRepository {
    public ResultSet getAllDetails();
    boolean addDetails(SystemAccess systemAccess);
    boolean deleteDetails(String empId);
    boolean updateDetails(SystemAccess systemAccess);
    ResultSet searchEmployee(String empId);
}
