package SampleServlet;

import SampleServlet.model.Todo;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Service {

    public static Todo getTodo(int id) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/todos/" + id);
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        Gson gson = new Gson();
        Todo todo = gson.fromJson(in, Todo.class);
        in.close();
        return todo;
    }

    private static String todoToHTMLRow(Todo todo) {
        return "<tr>" +
                "<td>" +
                todo.getUserId() +
                "</td><td>" +
                todo.getId() +
                "</td><td>" +
                todo.getTitle() +
                "</td><td>" +
                todo.isCompleted() +
                "</td>" +
                "</tr>";
    }

    public static String todosToHTMLTable(List<Todo> todoList) {
        StringBuilder stringBuilder = new StringBuilder("<div align=\"center\">")
                .append("<br/><br/><br/><br/>")
                .append("<table>")
                .append("<tr>")
                .append("<th>User Id</th>")
                .append("<th>Id</th>")
                .append("<th>Title</th>")
                .append("<th>Completed</th>")
                .append("</tr>");

        for (Todo todo : todoList) {
            stringBuilder.append(todoToHTMLRow(todo));
        }

        return stringBuilder
                .append("</table>")
                .append("<br/>")
                .append("<button onclick=\"window.location.href='/';\">Go back</button>")
                .append("</div>")
                .toString();
    }

    public static String notFoundHTML() {
        return "<div align=\"center\">" +
                "<br/><br/><br/><br/>" +
                "<h3>" +
                "No existe un ítem con el identificador dado" +
                "</h3>" +
                "<br/>" +
                "<button onclick=\"window.location.href='/';\">Go back</button>" +
                "</div>";
    }
}
