package com.resrt_assuerd;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*Google Map API - Validating Headers

https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s

SUCCESS RESPONSE : Returns list of super markets

"Headers:
Content-Encoding →gzip
Content-Type →application/xml; charset=UTF-8
Server →scaffolding on HTTPServer2
"
*/

public class TC004_GET_Request_printAllHeadders {

 @Test
 void getWeatherDetails()
 {
  
  //Specify base URI
  RestAssured.baseURI="https://maps.googleapis.com";
  
  //Request object
  RequestSpecification httpRequest=RestAssured.given();
  
  //Response object
  Response response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
  
  //print response in console window
  String responseBody=response.getBody().asString();
  System.out.println("Response Body is:" +responseBody);
  
  //headers follows map data structures in java
  // map ds consists of keys and values. so every header has  keys and values
  
   Headers allheaders = response.headers();   //headers()  --> returs headers Datatype.
   
   // check
   for (Header header: allheaders )
   {   //    header holds the key and value so we need to print them                                 
	   System.out.println(header.getName()+ "    "+header.getValue() );    //getName() --> returns key, 
   }
  
  
  
  
  
  
  // what header we need to validate is cross-checked by postman
  //validating headers
 
 }
 
}