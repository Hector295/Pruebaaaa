package Servlet;

import Bean.Estadios;
import Bean.SeleccionesNacionales;
import Dao.DaoSeleccionesNacionales;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SeleccionesServlet", urlPatterns = {"/SeleccionesServlet",""})
public class SeleccionesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoSeleccionesNacionales daoSeleccionesNacionales = new DaoSeleccionesNacionales();
        RequestDispatcher view;

        switch (action){

            case "guardar":
                SeleccionesNacionales seleccion1 = new SeleccionesNacionales();
                seleccion1.setNombre(request.getParameter("nombre"));
                seleccion1.setTecnico(request.getParameter("tecnico"));
                seleccion1.setEstadios_idEstadio(Integer.parseInt(request.getParameter("estadio")));
                ArrayList<SeleccionesNacionales> seleccionesNacionalesLista = daoSeleccionesNacionales.listarSelecciones();
                boolean evaluar = false;
                for(SeleccionesNacionales s : seleccionesNacionalesLista){
                    if(s.getNombre().equalsIgnoreCase(request.getParameter("nombre"))){
                        evaluar = true;
                        break;
                    }
                }
                if(!request.getParameter("nombre").equalsIgnoreCase("")
                      &&!request.getParameter("tecnico").equalsIgnoreCase("")
                    &&!request.getParameter("estadio").equalsIgnoreCase("")
                        && evaluar == false){
                    daoSeleccionesNacionales.crearSeleccion(seleccion1);
                }else{
                    ArrayList<Estadios> estadios = daoSeleccionesNacionales.listarEstadios();
                    request.setAttribute("lista", estadios);
                    view = request.getRequestDispatcher("/Selecciones/FormCreate.jsp");
                    view.forward(request, response);
                }
                    response.sendRedirect(request.getContextPath()+"/SeleccionesServlet");

                break;
            case "actualizar":
                SeleccionesNacionales seleccion2 = new SeleccionesNacionales();
                seleccion2.setIdSeleccionesNacionales(Integer.parseInt(request.getParameter("id")));
                seleccion2.setNombre(request.getParameter("nombre"));
                seleccion2.setTecnico(request.getParameter("tecnico"));
                seleccion2.setEstadios_idEstadio(Integer.parseInt(request.getParameter("estadio")));

                if(!seleccion2.getNombre().equalsIgnoreCase("")
                        &&!seleccion2.getTecnico().equalsIgnoreCase("")
                        &&!request.getParameter("estadio").equalsIgnoreCase("")){
                    daoSeleccionesNacionales.actualizarSeleccion(seleccion2);
                }else{
                    ArrayList<Estadios> estadios2 = daoSeleccionesNacionales.listarEstadios();
                    request.setAttribute("lista", estadios2);
                    request.setAttribute("seleccion", seleccion2);
                    view = request.getRequestDispatcher("/Selecciones/FormEdit.jsp");
                    view.forward(request, response);
                }
                    response.sendRedirect(request.getContextPath()+"/SeleccionesServlet");

                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoSeleccionesNacionales daoSeleccionesNacionales = new DaoSeleccionesNacionales();
        RequestDispatcher view;

        switch (action){
            case "lista":
                ArrayList<SeleccionesNacionales> listaselecciones1 = daoSeleccionesNacionales.listarSelecciones();
                request.setAttribute("lista", listaselecciones1);
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);

                break;
            case "crear":
                ArrayList<Estadios> estadios = daoSeleccionesNacionales.listarEstadios();
                request.setAttribute("lista", estadios);
                view = request.getRequestDispatcher("/Selecciones/FormCreate.jsp");
                view.forward(request, response);
                break;
            case "editar":
                ArrayList<Estadios> estadios2 = daoSeleccionesNacionales.listarEstadios();
                int id = Integer.parseInt(request.getParameter("id"));
                SeleccionesNacionales seleccion1 = daoSeleccionesNacionales.buscarSeleccion(id);

                if(seleccion1 != null){
                    request.setAttribute("lista", estadios2);
                    request.setAttribute("seleccion", seleccion1);
                    view = request.getRequestDispatcher("/Selecciones/FormEdit.jsp");
                    view.forward(request, response);
                }else{
                    response.sendRedirect(request.getContextPath()+"/SeleccionesServlet");
                }
                break;
            case "borrar":
                int id2 = Integer.parseInt(request.getParameter("id"));
                if(daoSeleccionesNacionales.buscarSeleccion(id2) != null){
                    daoSeleccionesNacionales.borrarSeleccion(id2);
                }
                    response.sendRedirect(request.getContextPath() + "/SeleccionesServlet");
                break;

        }

    }
}
