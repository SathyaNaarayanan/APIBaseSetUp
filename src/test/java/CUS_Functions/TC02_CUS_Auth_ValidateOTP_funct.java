package CUS_Functions;

import java.io.IOException;
import java.util.Map;

import CUS_POJO.CUS_TC02_Auth_ValitateOTP;
import Utility.ExcelOperation;

public class TC02_CUS_Auth_ValidateOTP_funct {
	
	public static CUS_TC02_Auth_ValitateOTP validateOTP_func() throws IOException {
		CUS_TC02_Auth_ValitateOTP val_otp_func = new CUS_TC02_Auth_ValitateOTP();
		try {
			ExcelOperation readExcelData = new ExcelOperation();
			Map<String, String> hm = readExcelData.ReadExcelData("CUS_DataReceive");
			Thread.sleep(3000);
			val_otp_func.setOtpNumber(hm.get("otp"));
			val_otp_func.setUserType("CUS");
			val_otp_func.setUuid(hm.get("uuid"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return val_otp_func;
		
	}

}
