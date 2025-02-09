package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LocatorsReader {
	public Properties getLocatorsFrom(String fileName) 
	{
		Properties prop = new Properties();
		try(FileInputStream givenFile = new FileInputStream(FrameworkConstants.LOCATORS_PATH+fileName+".properties")) {
			prop.load(givenFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	
	
}
