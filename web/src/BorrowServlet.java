import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.bcos.credit.app.Main;
import org.fisco.bcos.web3j.abi.datatypes.Address;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Main m = new Main();

        String add = req.getParameter("address");
        String num = req.getParameter("num");
        String time = req.getParameter("time");
        Boolean ok;

        try {
            ok = m.borrow(add, time, num);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        if(ok) pw.write("success");
        else pw.write("fail");

        pw.flush();
    }
}
