package Repository;

import java.sql.ResultSet;

public interface SystemAccessRepository {
    public ResultSet getAllDetails();
    ResultSet searchEmployee(String empId);
}
