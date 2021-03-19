package CUS_Functions;

import java.io.IOException;
import java.util.Map;

import CUS_POJO.CUS_TC03_Auth_SendOTP;
import Utility.ExcelOperation;

public class TC03_CUS_Auth_SendOTP_funct {

	
	public static CUS_TC03_Auth_SendOTP sendOTP_func() throws IOException {
		CUS_TC03_Auth_SendOTP send_otp_func = new CUS_TC03_Auth_SendOTP();
		try {
		
			ExcelOperation readExcelData = new ExcelOperation();
			Map<String, String> hm = readExcelData.ReadExcelData("CUS_Input");
			Thread.sleep(3000);
			System.out.println("InputValues : " + hm);
			send_otp_func.setUserType(hm.get("userType"));
			send_otp_func.setMobileNo(hm.get("mobileNo"));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return send_otp_func;
		
	}
}
