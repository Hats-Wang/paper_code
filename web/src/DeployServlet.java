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

        resp.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = resp.getWriter();
        pw.write("<head>\n" +
                "    <title>获取接口地址</title>\n" +
                "</head>\n" +
                "\n" +
                "<style>\n" +
                "    body{\n" +
                "        background: url(\"./picture/back.jpg\")center no-repeat;\n" +
                "        background-size:100%,100%;\n" +
                "    }\n" +
                "    div{\n" +
                "        margin:10cm 3cm 8cm 5cm;\n" +
                "    }\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "<div>\n" +
                "    <p style=\"font-size:40px;\"><b>接口地址：</b> " + add + "</p><br>\n" +
                "</div>\n" +
                "</body>");
        pw.flush();
    }
}
