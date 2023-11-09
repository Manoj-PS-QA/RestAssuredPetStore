package api.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;


import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints2Prop;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2Prop {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		//Log
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority= 1)
	public void testPostUser() {
		logger.debug("***********Creating the User************");
		Response response= UserEndpoints2Prop.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***********Created the user************");
	
		
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		logger.debug("***********Reading the User************");
		Response response=UserEndpoints2Prop.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		
	}
	
	@Test (priority=3)
	public void testUpdateUserByName() {
		
		logger.info("***********Updating the User************");
		//update data using payload, Here user name is static
		//without username all the other fields can be updated
		//Here we are updating firstname, lastname and email as they are created once
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		// here with respect to username we are updating
		// this is used to represent that particular username
		Response response= UserEndpoints2Prop.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Checking data after update
		Response responseAfterUpdate= UserEndpoints2Prop.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.debug("***********Updated the User************");
		
	}
	
	@Test (priority=4)
	public void testDeleteUserByName() {
		logger.info("***********Deleting the User************");
		Response response= UserEndpoints2Prop.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.debug("***********User Deleted************");
	}
	
	
	

}






















