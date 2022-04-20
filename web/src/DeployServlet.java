import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.bcos.credit.app.Main;
import org.fisco.bcos.web3j.abi.datatypes.Address;

@WebServlet("/deploy")
public class DeployServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Main m = new Main();
        String add = null;
        try {
            add = m.Deploy().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>" + add + "</h1>");
        pw.flush();
    }
}
