package util;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBPropertyUtil {
    public static String getConnectionString(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = DBPropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
        
        if (inputStream == null) {
            throw new IOException("Unable to find properties file.");
        }
        
        properties.load(inputStream);
        return properties.getProperty("db.url");
    }
}
