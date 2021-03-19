package CUS;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import CUS_Functions.TC01_CUS_Auth_SignUp_funct;
import Utility.API_Resources;
import Utility.ExtentTestNGReportBuilder;
import Utility.Helper_func;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class TC01_CUS_Auth_SignUp extends ExtentTestNGReportBuilder {
	
	
	public Response response;
	public RequestSpecification request;
	public ExtentTest test;
	
	@Test(priority=0)
	public void BeforeTest_LaunchURI() {
		
		try {
			test = extent.createTest("TC01_CUS_Auth_SignUp");
			//request = given().spec(Helper_func.req_specification()).body(TC01_CUS_Auth_SignUp_funct.sigUp_funct());
			request = given().spec(Helper_func.req_specification()).body(Helper_func.Read_Payload("CUS_Payload", "Auth_SignUp"));
			Thread.sleep(3000);
			test.log(Status.PASS, "Request - Launch URI");
			//test.pass(MarkupHelper.createLabel("Launch URI TestCase Passed", ExtentColor.GREEN));
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
	}

	@Test(priority=1)
	public void Test_SignUp(){
		try {
			
			//API_Resources  apiResources =  API_Resources.valueOf("Auth_SignUp");
			//response = request.when().post(apiResources.getResources()).then().spec(Helper_func.res_specification()).extract().response();
			response = request.when().post(Helper_func.GetResourceName("Auth_SignUp")).then().spec(Helper_func.res_specification()).extract().response();
			System.out.println(response.asString());
			Assert.assertEquals(response.getStatusCode(), 200);
			
			test.log(Status.PASS, "Test SignUp executed with response : "+ response.asString());
			//test.pass(MarkupHelper.createLabel("SignUp TestCase Passed", ExtentColor.GREEN));
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
		
	}
	
	@Test(priority=2)
	public void Save_ResponseData() {
		try {
			
			String otp = Helper_func.getJsonPath_StringValue(response, "data.otp");
			String uuid = Helper_func.getJsonPath_StringValue(response, "data.uuid");
			System.out.println(otp+ ", "+ uuid);
			Helper_func.Write_Data_Into_Excel("CUS_DataReceive", "otp", otp);
			Helper_func.Write_Data_Into_Excel("CUS_DataReceive", "uuid", uuid);
			test.log(Status.PASS, "Response data save in Excel");
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
		extent.flush();
	}
	
}
