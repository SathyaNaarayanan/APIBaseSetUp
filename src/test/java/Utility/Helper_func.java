package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Helper_func {

	public static RequestSpecification req_specification() throws IOException {

		RequestSpecification req_spec = new RequestSpecBuilder().setBaseUri(getGlobalValue("BaseURI")).
					addHeader("Content-Type","application/json").build(); 
		return req_spec; 
	}
	
	public static ResponseSpecification res_specification(){ 
		try {
			ResponseSpecification res_spec = new ResponseSpecBuilder().build();
			return res_spec;

		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public void test() {
		System.out.println("");;
	}


	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream inputFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Utility\\global.properties");
		prop.load(inputFile);
		return prop.getProperty(key);
	}
	
	public static String getJsonPath_StringValue(Response res, String key) {
		String resp = res.asString();
		JsonPath js = new JsonPath(resp);
		return js.getString(key).toString();
	}
	
	public static void Write_Data_Into_Excel(String sheetName, String rowName, String value) {
		try {
			ExcelOperation excelOperation = new ExcelOperation();
			excelOperation.Save_responseDataToExcel(sheetName, rowName, value);
			Thread.sleep(5000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String Read_Payload(String sheetName, String resource) {
		String myPayload = "";
		try {
			ExcelOperation excelOperation = new ExcelOperation();
			String endPoint = GetResourceName(resource);
			myPayload = excelOperation.GetPayload(sheetName, endPoint);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return myPayload;
	}
	
	public static String GetResourceName(String resource) {
		String endPoint = "";
		try {
			API_Resources  apiResources =  API_Resources.valueOf(resource);
			endPoint = apiResources.getResources();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return endPoint;
	}
	
}
