package com.henvealf.learn.jetty.first;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * servlet Handler 的使用。
 *
 * @author hongliang.yin/Henvealf
 * @date 2019-07-23
 */
public class ServletHandlerLearn {

    public static void main(String[] args) throws Exception {
        final Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(HelloServlet.class, "/hello");
        handler.addServletWithMapping(new ServletHolder(new CountServlet()), "/count");
        server.start();

        server.join();
    }

    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
    }

    public static class CountServlet extends HttpServlet {
        private int num = 0;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            resp.setStatus(HttpServletResponse.SC_OK);
            num ++;
            resp.getWriter().println(String.format("<h1>Count %s</h1>", num));
        }
    }

}
