package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties pro;

	public Properties init_properties() throws FileNotFoundException {
		pro = new Properties();

		File fis = new File("src/test/resources/propertyfiles/config.properties");
		FileInputStream FI = new FileInputStream(fis);

		pro = new Properties();

		try {
			pro.load(FI);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
}
