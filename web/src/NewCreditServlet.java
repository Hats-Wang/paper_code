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

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        pw.write("create success" + newCreditAddress.toString());
        pw.flush();
    }
}
