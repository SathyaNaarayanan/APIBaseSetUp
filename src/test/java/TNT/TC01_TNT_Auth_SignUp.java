package TNT;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TNT_Functions.TC01_Auth_SignUp_funct;
import Utility.API_Resources;
import Utility.ExtentTestNGReportBuilder;
import Utility.Helper_func;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.*;


public class TC01_TNT_Auth_SignUp extends ExtentTestNGReportBuilder {
	
	
	public Response response;
	public RequestSpecification request;
	ExtentTest test;
	
	@Test(priority=0)
	public void BeforeTest_LaunchURI() {
		try {
			test = extent.createTest("TC01_TNT_Auth_SignUp");
			request = given().spec(Helper_func.req_specification()).body(Helper_func.Read_Payload("TNT_Payload", "Auth_SignUp"));
			Thread.sleep(3000);
			test.log(Status.PASS, "Request - Launch URI");
		}
		catch(Exception e) {
			test.log(Status.FAIL, e.getMessage());
		}
	}
	
	@Test(priority=1)
	public void Test_SignUp() {
		try {
			System.out.println("Test_SignUp started");
			response = request.when().post(Helper_func.GetResourceName("Auth_SignUp")).then().spec(Helper_func.res_specification()).extract().response();
			System.out.println(response.asString());
			Assert.assertEquals(response.getStatusCode(), 200);
			assertThat(response.asString(), matchesJsonSchemaInClasspath("TC1.json"));
			test.log(Status.PASS, "Test SignUp executed with response : "+ response.asString());
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
