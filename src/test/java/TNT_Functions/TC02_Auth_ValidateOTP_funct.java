package TNT_Functions;

import java.io.IOException;
import java.util.Map;

import TNT_POJO.TNT_TC02_Auth_ValitateOTP;
import Utility.ExcelOperation;

public class TC02_Auth_ValidateOTP_funct {
	
	public static TNT_TC02_Auth_ValitateOTP validateOTP_func() throws IOException {
		TNT_TC02_Auth_ValitateOTP val_otp_func = new TNT_TC02_Auth_ValitateOTP();
		
		try {
			ExcelOperation readExcelData = new ExcelOperation();
			Map<String, String> hm = readExcelData.ReadExcelData("TNT_DataReceive");
			
			val_otp_func.setOtpNumber(hm.get("otp"));
			val_otp_func.setUserType("TNT");
			val_otp_func.setUuid(hm.get("uuid"));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return val_otp_func;
		
	}

}
