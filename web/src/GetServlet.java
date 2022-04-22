import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.bcos.credit.app.Main;
import org.bcos.credit.sample.CreditData;

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

        resp.setContentType("text/html;charset=utf-8;");
        PrintWriter pw = resp.getWriter();
        pw.write("<head>\n <title>企业征信数据</title>\n </head>\n<style>\n body{\n" +
                "      background: url(\"./picture/company.jpg\")center no-repeat;\n" +
                "      background-size:100%,100%;\n" +
                "    }\n div {margin:8cm 10cm 5cm 15cm;}\n p {display:inline;}\n</style>\n<body>\n<div>\n" +
                "<p style=\"font-size:40px;\"><b>公司名称：</b>" + "  " + data.getCompanyName()+ "</p><br><br>\n" +
                "<p style=\"font-size:40px;\"><b>公司信用分数：</b>" + data.getGrade().toString() + " </p><br><br>\n" +
                "<p style=\"font-size:40px;\"><b>公司是否抵押：</b>" + data.getPledge().toString() + " </p><br><br>\n" +
                "<p style=\"font-size:40px;\"><b>公司资产总额：</b>" + data.getCompanyValue().toString() + " </p><br><br>\n" +
                "</div> \n </body> ");
        pw.flush();
    }
}
