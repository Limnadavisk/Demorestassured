package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

import org.apache.commons.io.IOUtils;
import java.io.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.baseURI;



public class SoapXMLRequest {
	
	@Test
	public void validateSoapXML() throws IOException {
		
		File file = new File("./SoapRequest/Add.xml");
		if(file.exists())
			System.out.println("  >>  file exists");
		FileInputStream fileInputStream = new FileInputStream(file);
		String  requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		
		baseURI = "http://www.dneonline.com/";
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("calculator.asmx").
		then().
			statusCode(200).log().body().
		and().
			body("//*:AddResult.text()", equalTo("5"));
	
	}
	

}
