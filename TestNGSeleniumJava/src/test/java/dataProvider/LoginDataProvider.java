package dataProvider;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
	
	 @DataProvider(name = "InValidCredentials")
	    public Object[][] getInValidCredentials() {
	        return new Object[][]{
	            {"Username1", "password1"}
	        };
	    }
	 
	 @DataProvider(name = "ValidCredentials")
	    public Object[][] getValidCredentials() {
	        return new Object[][]{
	            {"standard_user", "secret_sauce"}
	        };
	    }
}

