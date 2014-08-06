package ske.part.partsregister.infrastructure.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ske.part.partsregister.infrastructure.bootstrap.PartsregisterApplication;

public class TestServer {

    public static void main(String[] args) {
        Server server = new Server(8180);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder jerseyServlet = context
                .addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/webapi/*");
        jerseyServlet.setInitOrder(1);
        jerseyServlet
                .setInitParameter("jersey.config.server.provider.packages", "ske.part.partsregister.interfaces.rest");
        String appName = PartsregisterApplication.class.getCanonicalName();
        jerseyServlet.setInitParameter("javax.ws.rs.Application", appName);

        ServletHolder staticServlet = context.addServlet(DefaultServlet.class, "/*");
        staticServlet.setInitParameter("resourceBase", "src/main/webapp");
        staticServlet.setInitParameter("pathInfoOnly", "true");

        try {
            server.start();
            server.join();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

}
