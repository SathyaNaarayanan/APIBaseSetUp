 package TNT;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TNT_Functions.TC01_Auth_SignUp_funct;
import TNT_Functions.TC02_Auth_ValidateOTP_funct;
import Utility.API_Resources;
import Utility.ExtentTestNGReportBuilder;
import Utility.Helper_func;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_TNT_Auth_ValidateOTP extends ExtentTestNGReportBuilder{

	public Response response;
	public RequestSpecification request;
	ExtentTest test;
	
	@Test(priority=0)
	public void BeforeTest_LaunchURI() {
		try {
			test = extent.createTest("TC02_TNT_Auth_ValidateOTP");
			//request = given().spec(Helper_func.req_specification()).body(TC02_Auth_ValidateOTP_funct.validateOTP_func());
			request = given().spec(Helper_func.req_specification()).body(Helper_func.Read_Payload("TNT_Payload", "Auth_ValidateOTP"));
			Thread.sleep(3000);
			test.log(Status.PASS, "Request - Launch URI");
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
		
	}
	
	@Test(priority=1)
	public void Test_ValidateOTP(){
		try {
			//API_Resources  apiResources =  API_Resources.valueOf("Auth_ValidateOTP");
			//response = request.when().post(apiResources.getResources()).then().spec(Helper_func.res_specification()).extract().response();
			response = request.when().post(Helper_func.GetResourceName("Auth_ValidateOTP")).then().spec(Helper_func.res_specification()).extract().response();
			System.out.println(response.asString());
			Assert.assertEquals(response.getStatusCode(), 200);
			test.log(Status.PASS, "Test ValidateOTP executed with response : " + response.asString());
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
	}
	
	@Test(priority=2)
	public void Save_ResponseData() {
		try {
			String Bearer = Helper_func.getJsonPath_StringValue(response, "data.Bearer");
			System.out.println("BarearToken : " +Bearer);
			Helper_func.Write_Data_Into_Excel("TNT_DataReceive", "Bearer", Bearer);
			test.log(Status.PASS, "Response data save in Excel");
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
		extent.flush();
	}
	
	
	
}
