import java.util.Random;

public class OTP {

    public static int otpNumber;

    public static int generateOTP(){
        Random random=new Random();

        int randomInt=random.nextInt(100000);
        otpNumber=randomInt;
        return otpNumber;

    }

}
