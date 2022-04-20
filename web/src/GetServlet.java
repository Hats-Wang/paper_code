import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.bcos.credit.app.Main;
import org.bcos.credit.sample.CreditData;
import org.fisco.bcos.web3j.abi.datatypes.Address;

@WebServlet("/get")
public class GetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Main m = new Main();

        String add = req.getParameter("address");
        Boolean ok;
        CreditData data;
        try {
            data = m.Get(add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.write(data.getCompanyName());
        pw.write(String.valueOf(data.getGrade()));
        pw.flush();
    }
}
