package osama.learning.container;

import osama.learning.container.ServerListener;
import osama.learning.container.servlet.Servlet;
import osama.learning.container.servlet.ServletsContainer;
import osama.learning.container.servletcreation.ServletsCreator;
import osama.learning.container.servletcreation.ServletsPropertiesFileReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ServerStarter {
    private final ServerListener listener;
    private final ServletsCreator servletsCreator;
    private final ServletsContainer servletsContainer;
    private final ServletsPropertiesFileReader servletsPropertiesReader;

    public ServerStarter(ServerListener listener,
                         ServletsCreator servletsCreator,
                         ServletsContainer servletsContainer,
                         ServletsPropertiesFileReader servletsPropertiesReader) {
        this.listener = listener;
        this.servletsCreator = servletsCreator;
        this.servletsContainer = servletsContainer;
        this.servletsPropertiesReader = servletsPropertiesReader;
    }

    public void startServer() {
        Properties properties = getProperties();
        loadServletsToContainer(properties);
        Thread listenerThread = new Thread(listener::listen);
        listenerThread.start();
    }

    private Properties getProperties() {
        try {
            return servletsPropertiesReader.readProperties();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }

    private void loadServletsToContainer(Properties properties) {
        properties.forEach((path, servletName) -> {
            try {
                Servlet servlet = servletsCreator.createServlet((String) servletName);
                servletsContainer.add((String) path, servlet);
            } catch (NoSuchMethodException
                    | InvocationTargetException
                    | InstantiationException
                    | IllegalAccessException
                    | ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        });
    }
}
