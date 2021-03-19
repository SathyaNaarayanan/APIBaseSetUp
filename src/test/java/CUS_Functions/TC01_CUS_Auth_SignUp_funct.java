package CUS_Functions;

import java.io.IOException;
import java.util.Map;

import CUS_POJO.CUS_TC01_Auth_SigUp;
import Utility.ExcelOperation;

public class TC01_CUS_Auth_SignUp_funct {

	
	public static CUS_POJO.CUS_TC01_Auth_SigUp sigUp_funct() throws IOException {
		CUS_TC01_Auth_SigUp signUp = new CUS_TC01_Auth_SigUp();
		
		try {
			ExcelOperation readExcelData = new ExcelOperation();
			Map<String, String> hm = readExcelData.ReadExcelData("CUS_Input");
			Thread.sleep(3000);
			System.out.println("InputBody : "+ hm);
			signUp.setCountryCode("+"+hm.get("countryCode"));
			signUp.setMobileNo(hm.get("mobileNo"));
			signUp.setUserType(hm.get("userType"));
			signUp.setEmail(hm.get("email"));
			signUp.setPassword(hm.get("password"));
			signUp.setIsSignUp(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return signUp;
	}
	
}
