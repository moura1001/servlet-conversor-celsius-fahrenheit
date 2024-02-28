import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@WebServlet("/converter")
public class ConversorEscalasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        double temperatura = Double.parseDouble(request.getParameter("temperatura"));
        String converterPara = request.getParameter("escala");
        double resultado;
        String resposta;
        
        switch (converterPara) {
            case "F":
                // conversão de Celsius (C) para Fahrenheit (F)
                resultado = ((temperatura * 9.0) / 5.0) + 32.0;
                resposta = "Ao converter de Celsius para Fahrenheit";
                break;
            case "C":
                // conversão de Fahrenheit (F) para Celsius (C)
                resultado = ((temperatura - 32.0) * 5.0) / 9.0;
                resposta = "Ao converter de Fahrenheit para Celsius";
                break;
            default:
                // considera como padrão a conversão de Celsius (C) para Fahrenheit (F)
                resultado = ((temperatura * 9.0) / 5.0) + 32.0;
                resposta = "Ao converter de Celsius para Fahrenheit";
        }
        resposta += " o valor de " + df.format(temperatura) + ", o resultado é " + df.format(resultado);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Conversor escalas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + resposta + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
