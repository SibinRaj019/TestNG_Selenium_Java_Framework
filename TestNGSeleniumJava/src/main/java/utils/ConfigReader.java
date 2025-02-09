package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	public Properties readProperties(String fileName) 
	{
		Properties prop = new Properties();
		try(FileInputStream givenFile = new FileInputStream(FrameworkConstants.CONFIG_PROPERTIES_PATH)) {
			prop.load(givenFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
