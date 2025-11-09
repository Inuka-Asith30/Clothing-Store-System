package service;

import java.time.LocalTime;

public class LoginFormServiceImpl implements LoginFormService {

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

        if(employeeID.equals("admin") && password.equals("admin")){
            return true;
        }

        return false;
    }


}
