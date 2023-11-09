package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created for perform curd i.e. create, update, read and delete operations

// Here we get url's from properties file

public class UserEndpoints2Prop {
	
	//Method created for getting URL's from properties file
		static ResourceBundle getURL(){
			ResourceBundle routes= ResourceBundle.getBundle("routes");  // routes represent properties file name by default ResourceBundle class will find properties file location so giving file name is enough
			return routes;
		}
		

	public static Response createUser(User payload)
	{
		String post_url= getURL().getString("post_url"); // key name from properties file
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(post_url);
		
		return response;	
		
	}
	public static Response readUser(String userName)
	{
		String get_url= getURL().getString("get_url");
		
		Response response = given()
				.pathParam("username", userName)
			.when()
				.get(get_url);
		
		return response;	
		
	}
	public static Response updateUser(String userName, User payload)
	{
		String put_url= getURL().getString("put_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when()
				.put(put_url);
		
		return response;	
		
	}
	public static Response deleteUser(String userName)
	{
		String delete_url= getURL().getString("delete_url");
		
		Response response = given()
				.pathParam("username", userName)
			.when()
				.delete(delete_url);
		
		return response;	
		
	}

}