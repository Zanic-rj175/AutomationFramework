package genericUtility;
/*
 * this class consists of method related to property file
 * @Saumya
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;

public class PropertyFileUtility {
	/**
	 * This method is used to read data from property file provided key
	 * @param key
	 * @return 
	 * @return
	 * @throws EncryptedDocumentException 
	 * @throws IOException
	 */
	 public String toReadDataFromPropertyFile(String key) throws IOException {
		FileInputStream file = new FileInputStream(IconstantUtility.propertyFileUtility);
        Properties prop = new Properties();
        prop.load(file);
        String value = prop.getProperty(key);
        return value;
	}
		
}
