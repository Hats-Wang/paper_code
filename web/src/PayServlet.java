import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.bcos.credit.app.Main;
import org.fisco.bcos.web3j.abi.datatypes.Address;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Main m = new Main();

        String add = req.getParameter("address");
        Boolean ok;

        ok = m.Payback(add);

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        if(ok) pw.write("success");
        else pw.write("fail");

        pw.flush();
    }

}
