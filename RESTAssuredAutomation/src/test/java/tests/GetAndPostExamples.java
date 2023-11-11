package tests;

import org.json.simple.JSONObject;

//import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAndPostExamples {
	
	@Test
	public void testGet() {
		
		baseURI = "https://reqres.in/api";
		given().
			get("/users?page=2").
		then().
			statusCode(200).body("data.first_name[4]", equalTo("George")).
			body("data.first_name", hasItems("George","Rachel")).log().all();
		
	}
	
	@Test
	public void testPost() {
		
	Map<String,Object> map = new HashMap<String, Object>();
	map.put("name", "Raghav");
	map.put("job","Teacher");
	System.out.println(map);
	JSONObject request = new JSONObject(map);
	System.out.println(request);
	System.out.println(request.toJSONString());  //to amke sure it converts to JSON
	
	
	//Map<String,Object> map1 = new HashMap<String, Object>();
	//map1.put("name", "Raghav");
	//map1.put("job","Teacher");
	//System.out.println(map);
	JSONObject request1 = new JSONObject();
	request1.put("name", "Limna");
	request1.put("job", "ClaimExaminer");
	
	System.out.println(request1.toJSONString());  
	
	baseURI = "https://reqres.in/api";
	
	given().
		header("content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request1.toJSONString()).
	when().
		post("/users").
	then().
		statusCode(201).
		log().all();
	
	}

}
