package TNT;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TNT_Functions.TC03_Auth_SendOTP_funct;
import Utility.API_Resources;
import Utility.ExcelOperation;
import Utility.ExtentTestNGReportBuilder;
import Utility.Helper_func;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC03_TNT_Auth_SendOTP extends ExtentTestNGReportBuilder {
	
	public Response response;
	public RequestSpecification request;
	ExtentTest test;
	
	@Test(priority=0)
	public void BeforeTest_LaunchURI(){
		try {
			test = extent.createTest("TC03_TNT_Auth_SendOTP");
			ExcelOperation readExcelData = new ExcelOperation();
			Map<String, String> hm = readExcelData.ReadExcelData("TNT_DataReceive");
			test.log(Status.PASS, "I get Bearer token : " + hm.get("Bearer"));
			Thread.sleep(3000);
			//request = given().header("Authorization", "Bearer "+hm.get("Bearer")).spec(Helper_func.req_specification()).body(TC03_Auth_SendOTP_funct.sendOTP_func());
			request = given().header("Authorization", "Bearer "+hm.get("Bearer")).spec(Helper_func.req_specification()).body(Helper_func.Read_Payload("TNT_Payload", "Auth_SendOTP"));
			Thread.sleep(2000);
			test.log(Status.PASS, "Request - Launch URI");
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
		
	}

	@Test(priority=1)
	public void Test_SendOTP() {
		try {
			test.log(Status.PASS, "Test_SendOTP started");
			//API_Resources  apiResources =  API_Resources.valueOf("Auth_SendOTP");
			//response = request.when().post(apiResources.getResources()).then().spec(Helper_func.res_specification()).extract().response();
			response = request.when().post(Helper_func.GetResourceName("Auth_SendOTP")).then().spec(Helper_func.res_specification()).extract().response();
			System.out.println(response.asString());
			test.log(Status.PASS, response.asString());
			Assert.assertEquals(response.getStatusCode(), 200);
			System.out.println("Test_SendOTP status code verified");
			test.log(Status.PASS, "Test SendOTP executed with response : "+ response.asString());
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}

		
	}
	
	@Test(priority=2)
	public void Save_ResponseData()  {
		try {
		String otp = Helper_func.getJsonPath_StringValue(response, "data.otp");
		String uuid = Helper_func.getJsonPath_StringValue(response, "data.uuid");
		System.out.println(otp+ ", "+ uuid);
		Helper_func.Write_Data_Into_Excel("TNT_DataReceive", "otp", otp);
		Helper_func.Write_Data_Into_Excel("TNT_DataReceive", "uuid", uuid);
		test.log(Status.PASS, "Response data save in Excel");
		
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
		extent.flush();
	}
	
	
	
}
