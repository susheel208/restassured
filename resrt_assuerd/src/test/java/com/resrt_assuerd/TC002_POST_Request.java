package com.resrt_assuerd;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*Register Customer API

Request Type: POST
http://restapi.demoqa.com/customer/register

BODY

{
   “FirstName” : “value”
   “LastName” : “value”,
   “UserName” : “value”,
   “Password” : “value”,
   “Email”        : “Value”
 }

SUCCESS RESPONSE

{
“SuccessCode”: “OPERATION_SUCCESS”,
“Message”: “Operation completed successfully”
}

STATUS CODE : 201

*/

public class TC002_POST_Request {
 
 @Test
 void RegistrationSuccessful()
 {
  /*steps involved in post
   * 1> we send request
   * 2> we pass data to the serever. and post req contains the data in json format
   *3> we create the body and  send the request along with the body
   */ 
  //Specify base URI
  RestAssured.baseURI="http://restapi.demoqa.com/customer";
  
  //Request object
  RequestSpecification httpRequest=RestAssured.given();
  
   
  //Request paylaod( body) sending along with post request
  JSONObject requestParams=new JSONObject();            //  used to create the body
  
  requestParams.put("FirstName","JohnXYZ");             // these are request paramnets in json format
  requestParams.put("LastName","XYZJohn");
  requestParams.put("UserName","JohnXYZ");
  requestParams.put("Password","JohnXYZxyx");
  requestParams.put("Email","JohnXYZ@gmail.com");
  
  httpRequest.header("Content-Type","application/json");   //  it rep the content type in json format as the header
  
  httpRequest.body(requestParams.toJSONString()); // attach above data to the request,  all the data is now attached a body
  
  //Response object
  Response response=httpRequest.request(Method.POST,"/register");
    
  //the above line will crate the new record into the server.
  
  //print response in console window
  
  String responseBody=response.getBody().asString();
  System.out.println("Response Body is:" +responseBody);
  
  //status code validation
  int statusCode=response.getStatusCode();
  System.out.println("Status code is: "+statusCode);
  Assert.assertEquals(statusCode, 201);
  
  //success code validation. .. as the success coed is genetred with in the response
  String successCode=response.jsonPath().get("SuccessCode");            // returns the value of successcode
  Assert.assertEquals(successCode, "OPERATION_SUCCESS");
  
 }
 

}