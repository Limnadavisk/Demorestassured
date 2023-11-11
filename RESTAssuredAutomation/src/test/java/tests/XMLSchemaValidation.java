package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class XMLSchemaValidation {
	
	@Test
	public void schemaValidation() throws IOException {
		File file = new File("./SoapRequest/Add.xml");
		if(file.exists())
			System.out.println("  >>  file exists");
		FileInputStream fileInputStream = new FileInputStream(file);
		String  requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		System.out.println("--------------");
		System.out.println(requestBody);
		System.out.println("--------------");
		
		baseURI = "http://www.dneonline.com/";
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("calculator.asmx").
		then().
			statusCode(200).log().all().
		and().
			body("//*:AddResult.text()", equalTo("5")).
		and().			
			assertThat().body(matchesXsdInClasspath("Calc.xsd"));
		
		
		
	}

}
