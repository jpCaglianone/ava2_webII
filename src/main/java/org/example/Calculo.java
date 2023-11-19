package org.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calculo")
public class Calculo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valor1 = request.getParameter("valor1");
        String valor2 = request.getParameter("valor2");
        String operacao = request.getParameter("operacao");

        try {
            float num1 = Float.parseFloat(valor1);
            float num2 = Float.parseFloat(valor2);
            float result = 0;
            boolean erroDiv = false;

            switch (operacao) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/":
                    if (num2 == 0) erroDiv = true;
                    else result = num1 / num2;
                    break;
            }

            if (!erroDiv) {
                request.setAttribute("resultado", num1 + " " + operacao + " " + num2 + " = " + result);
            } else {
                request.setAttribute("erroDivisao", "Erro: Divisão por zero.");
            }
        } catch (NumberFormatException e) {
            if (!valor1.matches("[0-9.]+")) {
                request.setAttribute("erroValor1", "Erro no Valor 1: não é um número.");
            }
            if (!valor2.matches("[0-9.]+")) {
                request.setAttribute("erroValor2", "Erro no Valor 2: não é um número.");
            }
        }

        request.getRequestDispatcher("calcula.jsp").forward(request, response);
    }
}
