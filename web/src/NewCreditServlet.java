import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.bcos.credit.app.Main;
import org.fisco.bcos.web3j.abi.datatypes.Address;

@WebServlet("/new")
public class NewCreditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Main m = new Main();

        String add = req.getParameter("address");
        String grade = req.getParameter("grade");
        String name = req.getParameter("name");
        String pledge = req.getParameter("pledge");
        String value = req.getParameter("value");

        Address newCreditAddress = null;
        try {
            newCreditAddress = m.NewCredit(add, grade, name, pledge, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = resp.getWriter();

        pw.write("<head>\n" +
                "    <title>企业征信地址</title>\n" +
                "</head>\n" +
                "\n" +
                "<style>\n" +
                "    body{\n" +
                "        background: url(\"./picture/back.jpg\")center no-repeat;\n" +
                "        background-size:100%,100%;\n" +
                "    }\n" +
                "    div{\n" +
                "        margin:10cm 3cm 8cm 3cm;\n" +
                "    }\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "<div>\n" +
                "    <p style=\"font-size:40px;\"><b>企业征信地址：</b> " + newCreditAddress.toString() + "</p><br>\n" +
                "</div>\n" +
                "</body>");

        pw.flush();
    }
}
