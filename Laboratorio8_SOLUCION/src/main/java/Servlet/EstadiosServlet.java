package Servlet;

import Bean.Estadios;
import Bean.SeleccionesNacionales;
import Dao.DaoEstadios;
import Dao.DaoSeleccionesNacionales;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EstadiosServlet", urlPatterns = {"/EstadiosServlet"})
public class EstadiosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoEstadios daoEstadios = new DaoEstadios();
        RequestDispatcher view;

        switch (action){
            case "lista":
                ArrayList<Estadios> estadiosArrayList = daoEstadios.listarEstadios();
                request.setAttribute("lista", estadiosArrayList);
                view = request.getRequestDispatcher("/Estadios/lista.jsp");
                view.forward(request, response);
                break;
        }


    }
}
