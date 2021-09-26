package osama.learning.container;


import osama.learning.container.servlet.ServletsContainer;
import osama.learning.container.servletcreation.ServletsCreator;
import osama.learning.container.servletcreation.ServletsPropertiesFileReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerStarter starter = new ServerStarter(
                new ServerListener(88),
                new ServletsCreator(),
                ServletsContainer.getInstance(),
                new ServletsPropertiesFileReader()
        );
        starter.startServer();
    }
}
