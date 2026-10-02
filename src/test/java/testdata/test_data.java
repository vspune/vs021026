package testdata;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class test_data {

	public AddPlace addplace(String name, String language, String address)
	{
		   AddPlace p1 = new AddPlace();
	
		    p1.setAccuracy(50);
		    p1.setAddress(address);
		    p1.setLanguage(language);
		    p1.setPhone_number("(+91) 983 893 3937");
		    p1.setWebsite("http://rahulshettyacademy.com");
		    p1.setName(name);
	
		    List<String> myList = new ArrayList<String>();
		    myList.add("shoe park");
		    myList.add("shop");
		    p1.setTypes(myList);
	
		    Location location = new Location();
		    location.setLat(-38.83494);
		    location.setLng(33.427362);
	
		    p1.setLocation(location);
		    
		    return p1;
			
	}
	
	public String deletedapi(String id)
	{
		//System.out.println("delete id is" + id);
		return id;
				
	}
	
	
}
