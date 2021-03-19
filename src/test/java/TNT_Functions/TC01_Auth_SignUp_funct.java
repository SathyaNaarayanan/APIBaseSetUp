package TNT_Functions;

import java.io.IOException;
import java.util.Map;

import TNT_POJO.TNT_TC01_Auth_SigUp;
import Utility.ExcelOperation;

public class TC01_Auth_SignUp_funct {

	
	public static TNT_TC01_Auth_SigUp sigUp_funct() throws IOException {
		
		TNT_TC01_Auth_SigUp signUp = new TNT_TC01_Auth_SigUp();
		try {
			ExcelOperation readExcelData = new ExcelOperation();
			
			Map<String, String> hm = readExcelData.ReadExcelData("TNT_Input");
			System.out.println("InputBody : "+ hm);
			Thread.sleep(3000);
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
	
	/*
	 * public static String UserInput() { return "{\r\n" +
	 * "    \"countryCode\":\"+65\",\r\n" + "    \"mobileNo\":\"9874561007\",\r\n" +
	 * "    \"userType\":\"TNT\",\r\n" +
	 * "    \"email\":\"testEmail007@gmail.com\",\r\n" +
	 * "    \"password\":\"testPwd@007\",\r\n" + "    \"isSignUp\": true\r\n" + "}";
	 * }
	 */
}
