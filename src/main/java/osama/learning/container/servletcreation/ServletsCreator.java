package osama.learning.container.servletcreation;

import osama.learning.container.servlet.Servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class ServletsCreator {
    public Servlet createServlet(String qualifiedName) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Servlet) Class.forName(qualifiedName).getConstructor().newInstance();
    }

    public List<Servlet> createAll(List<String> qualifiedNames) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Servlet> servlets = new LinkedList<>();
        for (String qualifiedName :
                qualifiedNames) {
            servlets.add(createServlet(qualifiedName));
        }
        return servlets;
    }
}
