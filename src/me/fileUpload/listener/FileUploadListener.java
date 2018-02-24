package me.fileUpload.listener;

import me.fileUpload.utils.FileUploadProperties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

public class FileUploadListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public FileUploadListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        InputStream inStream = getClass().getClassLoader().getResourceAsStream("upload.properties");
        Properties properties = new Properties();
        try {
            properties.load(inStream);
            for (Entry<Object, Object> property:properties.entrySet()) {
                String propertyName = (String) property.getKey();
                String propertyValue = (String) property.getValue();
                FileUploadProperties.getInstance().addProperty(propertyName, propertyValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

}
