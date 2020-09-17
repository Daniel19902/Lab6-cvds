package SampleServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

@WebServlet(urlPatterns = "/helloServlet")
public class SampleServlet extends HttpServlet {

    static final long serialVersionUID = 35L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Writer responseWriter = resp.getWriter();
        Optional<String> optionalName = Optional.ofNullable(req.getParameter("name"));
        String name = optionalName.isPresent() && !optionalName.get().isEmpty() ? optionalName.get(): "";

        resp.setStatus(HttpServletResponse.SC_OK);
        final String HTML =
                "<div align=\"center\">" +
                        "<br/><br/><br/><br/>" +
                        "<h3>Hi " + name + " , this is a greeting from the server!</h3>" +
                        "<br/>" +
                        "<button onclick=\"window.location.href='/';\">Go back</button>" +
                        "</div>";
        responseWriter.write(HTML);
        resp.setContentType("text/html");
        responseWriter.flush();
    }
}
