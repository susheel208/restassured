package com.dataDrivenTesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmp {

	
	@Test
	void postNewEmployees() {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		/* to crate the new data into the server we need to add the send the body alng with the request
		 * to send the body we nedd Json Object
		 */
		RequestSpecification httpRequest = RestAssured.given();
		
// ======> Creating the data for POST action========>	
		
		//Request paylaod( body) sending along with post request
	// here we created the data which we can send along with the post request 
		JSONObject requestParams=new JSONObject();            //  used to create the body
		  
		  requestParams.put("name","JohnXYZ");             // these are request paramnets 
		  requestParams.put("salary","100000");
		  requestParams.put("age","28");
		
		  httpRequest.header("Content-Type","application/json");   //  it Content type in header he json
		  httpRequest.body(requestParams.toJSONString()); // attach above data to the request,  all the data is now attached a body
		  // in the data is in hashmap format ie,. key and value pair --> covert to jsonstring
	
		  // ======> Sending the Post Request=====>
		  
		  Response response=httpRequest.request(Method.POST,"/create");  // we are using the create parameter --> which will be added to the api url
	
		 // After post action, Now the data is stored in the dataBase 
	
		  
 //  Now the Target is To assert the data present in the Database or not
		  String responseBody =  response.getBody().asString();               //
		  Assert.assertEquals(responseBody.contains("JohnXYZ"),true);   // contains is the string method
		  Assert.assertEquals(responseBody.contains("100000"),true);   // contains is the string method
		  Assert.assertEquals(responseBody.contains("28"),true);   // contains is the string method
		  
		  int statuscode = response.getStatusCode();    // getStatusCode is from the response
		  
		  Assert.assertEquals(statuscode,200);   // contains is the string method
			
		  
		  
		  
		  
		  
		  
	}
	
	
	
}
