package service;

import model.dto.Employee;

public interface LoginFormService {
    public String getTime();
    public boolean validateSubmit(String employeeID,String password);
}
