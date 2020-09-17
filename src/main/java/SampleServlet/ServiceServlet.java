package SampleServlet;

import SampleServlet.model.Todo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/todo")
public class ServiceServlet extends HttpServlet {

    static final long serialVersionUID = 34L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Writer responseWriter = resp.getWriter();
        resp.setContentType("text/html");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Todo> optionalTodo = Optional.ofNullable(Service.getTodo(id));

            if (optionalTodo.isPresent()) {
                Todo todo = optionalTodo.get();
                List<Todo> todoList = Collections.singletonList(todo);
                resp.setStatus(HttpServletResponse.SC_OK);
                responseWriter.write(Service.todosToHTMLTable(todoList));
                responseWriter.flush();
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                responseWriter.write(Service.notFoundHTML());
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            final String HTML =
                    "<div align=\"center\">" +
                            "<br/><br/><br/><br/>" +
                            "<h3>No se paso parámetro opcional, o el parámetro no contiene un número entero</h3>" +
                            "<br/>" +
                            "<button onclick=\"window.location.href='/';\">Go back</button>" +
                            "</div>";
            responseWriter.write(HTML);
        } catch (MalformedURLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            final String HTML =
                    "<div align=\"center\">" +
                            "<br/><br/><br/><br/>" +
                            "<h3>Error interno en el servidor</h3>" +
                            "<br/>" +
                            "<button onclick=\"window.location.href='/';\">Go back</button>" +
                            "</div>";
            responseWriter.write(HTML);
        } catch (Exception e) {
            final String HTML =
                    "<div align=\"center\">" +
                            "<br/><br/><br/><br/>" +
                            "<h3>Requerimiento inválido</h3>" +
                            "<br/>" +
                            "<button onclick=\"window.location.href='/';\">Go back</button>" +
                            "</div>";
            responseWriter.write(HTML);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Writer responseWriter = resp.getWriter();
        resp.setContentType("text/html");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Todo> optionalTodo = Optional.ofNullable(Service.getTodo(id));

            if (optionalTodo.isPresent()) {
                Todo todo = optionalTodo.get();
                List<Todo> todoList = Collections.singletonList(todo);
                resp.setStatus(HttpServletResponse.SC_OK);
                responseWriter.write(Service.todosToHTMLTable(todoList));
                responseWriter.flush();
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                responseWriter.write(Service.notFoundHTML());
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            final String HTML =
                    "<div align=\"center\">" +
                            "<br/><br/><br/><br/>" +
                            "<h3>No se paso parámetro opcional, o el parámetro no contiene un número entero</h3>" +
                            "<br/>" +
                            "<button onclick=\"window.location.href='/';\">Go back</button>" +
                            "</div>";
            responseWriter.write(HTML);
        } catch (MalformedURLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            final String HTML =
                    "<div align=\"center\">" +
                            "<br/><br/><br/><br/>" +
                            "<h3>Error interno en el servidor</h3>" +
                            "<br/>" +
                            "<button onclick=\"window.location.href='/';\">Go back</button>" +
                            "</div>";
            responseWriter.write(HTML);
        } catch (Exception e) {
            final String HTML =
                    "<div align=\"center\">" +
                            "<br/><br/><br/><br/>" +
                            "<h3>Requerimiento inválido</h3>" +
                            "<br/>" +
                            "<button onclick=\"window.location.href='/';\">Go back</button>" +
                            "</div>";
            responseWriter.write(HTML);
        }
    }
}
