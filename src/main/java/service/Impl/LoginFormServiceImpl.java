package service.Impl;

import javafx.beans.property.ReadOnlyProperty;
import lombok.Getter;
import model.dto.Employee;
import service.EmployeeService;
import service.LoginFormService;
import service.SystemAccessService;

import java.time.LocalTime;

public class LoginFormServiceImpl implements LoginFormService {

    SystemAccessService systemAccessService=new SystemAccessServiceImpl();
    EmployeeService employeeService=new EmployeeServiceImpl();

    public static Employee getEmployee;



    public String getTime(){
        LocalTime currentTime= LocalTime.now();
        //System.out.println(""+currentTime);

        if(currentTime.getHour()<12 && currentTime.getHour()>=00){
            return "Good Morning";
        }
        else if(currentTime.getHour()<=14 && currentTime.getHour()>=12){
            return "Good Afternoon";
        }
        else{
            return "Good Evening";
        }
    }

    @Override
    public boolean validateSubmit(String employeeID,String password) {

        getEmployee=employeeService.searchEmployee(employeeID);

        if(employeeID.equals(systemAccessService.searchEmployee(employeeID).getEmployeeId()) && password.equals(systemAccessService.searchEmployee(employeeID).getPassword())){

            return true;
        }

        return false;


    }





}
