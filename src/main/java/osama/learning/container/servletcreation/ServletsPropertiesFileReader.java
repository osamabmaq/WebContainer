package osama.learning.container.servletcreation;

import osama.learning.container.Main;
import osama.learning.container.ServerStarter;

import java.io.*;
import java.util.Properties;

public class ServletsPropertiesFileReader {
    private final File propertiesFile;

    public ServletsPropertiesFileReader() throws FileNotFoundException {
        this(new File(
                Main.class.getResource("/servlets.properties").getPath().replace("%20"," "))
        );
    }

    public ServletsPropertiesFileReader(File file) throws FileNotFoundException {
        propertiesFile = file;
        if (!propertiesFile.exists())
            throw new FileNotFoundException("Servlets properties file not existed");
        if (!file.getName().endsWith(".properties"))
            throw new IllegalArgumentException("Not properties file");
    }

    public Properties readProperties() throws IOException {
        FileReader reader = new FileReader(propertiesFile);
        Properties properties = new Properties();
        properties.load(reader);
        return properties;
    }
}
