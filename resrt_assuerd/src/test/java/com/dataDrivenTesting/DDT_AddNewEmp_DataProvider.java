package com.dataDrivenTesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DDT_AddNewEmp_DataProvider {

	
	@Test(dataProvider = "Empdataprovider")
	void postNewEmployees(String empName, String empSalary, String empAge ) {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		/* to crate the new data into the server we need to add the send the body alng with the request
		 * to send the body we nedd Json Object
		 */
		RequestSpecification httpRequest = RestAssured.given();
		
// ======> Creating the data for POST action========>	
		
		//Request paylaod( body) sending along with post request
	// here we created the data which we can send along with the post request 
		JSONObject requestParams=new JSONObject();            //  used to create the body
		  
		  requestParams.put("name", empName);             // these are request paramnets 
		  requestParams.put("salary",empSalary);
		  requestParams.put("age",empAge);
		
		  httpRequest.header("Content-Type","application/json");   //  it Content type in header he json
		  httpRequest.body(requestParams.toJSONString()); // attach above data to the request,  all the data is now attached a body
		  // in the data is in hashmap format ie,. key and value pair --> covert to jsonstring
	
		  // ======> Sending the Post Request=====>
		  
		  Response response=httpRequest.request(Method.POST,"/create");  // we are using the create parameter --> which will be added to the api url
	
		 // After post action, Now the data is stored in the dataBase 
	
		  
 //  Now the Target is To assert the data present in the Database or not
		  String responseBody =  response.getBody().asString();   
		  System.out.println("Body "+ responseBody);
			
		  
		  Assert.assertEquals(responseBody.contains(empName),true);   // contains is the string method
		  Assert.assertEquals(responseBody.contains(empSalary),true);   // contains is the string method
		  Assert.assertEquals(responseBody.contains(empAge),true);   // contains is the string method
		  
		  int statuscode = response.getStatusCode();    // getStatusCode is from the response
		  
		  Assert.assertEquals(statuscode,200);   // contains is the string method
			
		 		  
	}
	
	
	@DataProvider (name = "Empdataprovider")
	String[][] getEmpData()
	{
		String [][] empData= {{"abc123","30000","40"} , {"xyz123","40000","30"},{"pqr123","80000","50"}};
	return empData;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
