package api.endpoints;

/*
Only the URL's are maintained in this class from swagger 
Swagger url: https://petstore.swagger.io/
        ------User Module------
Create user(Post):https://petstore.swagger.io/v2/user
Get user (Get)://petstore.swagger.io/v2/user/{username}
Update user (Put):/petstore.swagger.io/v2/user/{username}
Delete user (Delete):https://petstore.swagger.io/v2/user/{username}
                     |---------Base url---------|----End point-----|username is path parameter
*/
public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User model
	public static String post_url= base_url+"/user";
	public static String get_url= base_url+"/user/{username}";
	public static String put_url= base_url+"/user/{username}";
	public static String delete_url= base_url+"/user/{username}";
	
	

}
