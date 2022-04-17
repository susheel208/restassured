  package com.restAssured.FunDoTesters;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*Weather API - Validate status code & Status line

http://restapi.demoqa.com/utilities/weather/city/  

Request Type: GET

http://restapi.demoqa.com/utilities/weather/city/Hyderabad

SUCCESS RESPONSE
{
“City”: “Hyderabad”,
“Temperature”: “31.49 Degree celsius”,
“Humidity”: “62 Percent”,
“Weather Description”: “scattered clouds”,
“Wind Speed”: “3.6 Km per hour”,
“Wind Direction degree”: “270 Degree”
}
STATUS CODE : 200                ---> after getting the api we need to valtidate the status code and status line

Status Line: HTTP/1.1 200 OK"*/

/*
 * For api testing:
 * 1) send the requset  ->we will get the response
 * we need to validat the following paraments:
 * 1- status code status line
 * 2-content 
 * 3-response body  --> in most cases the rest api returns response body in json format.
 * 4-headers
 * 5-how much time it has taken
 * 
 */
public class TC001_GET_Request {

	@Test
	void getweatherDetails() {  // RestAssured --> is the class
		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Request object creation
		RequestSpecification httpRequest = RestAssured.given();         //sends req to the server
// after sending the req we will get response,  so to store RESPONSE we ARE USING THE res
		// Response object to store the response, as we will get the response after sending the request.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");   // --> pass in the  parameter along with request
//  the above line will send the request and also store the response after recieving in it
		
		// print response in console window
		String responseBody = response.getBody().asString();   // response will be in json format so we need to covert into the string to print
		System.out.println("Response Body is:" + responseBody);

// note: status code and status line are the part of response so we need to use the object of response

		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

		// status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status line is:" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

/*
 * note: 
 * 
 * after sending the req we will get response,  so to store RESPONSE we ARE USING THE res
 *  Response object to store the response, as we will get the response after sending the request.
	will send the request and also store the response after recieving in it
 * 
 * status code and status line are the part of response so we need to use the object of response
 status code is an integer, 
 status line is a string 
 *
 *
 */

		
	}

}
