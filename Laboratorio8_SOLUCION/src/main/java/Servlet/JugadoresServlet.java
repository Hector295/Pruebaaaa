package Servlet;

import Bean.Estadios;
import Bean.Jugadores;
import Bean.SeleccionesNacionales;
import Dao.DaoEstadios;
import Dao.DaoJugadores;
import Dao.DaoSeleccionesNacionales;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "JugadoresServlet", urlPatterns = {"/JugadoresServlet"})
public class JugadoresServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoSeleccionesNacionales daoSeleccionesNacionales = new DaoSeleccionesNacionales();
        DaoJugadores daoJugadores = new DaoJugadores();
        RequestDispatcher view;
        ArrayList<String> posiciones = new ArrayList<>();
        posiciones.add("Portero");
        posiciones.add("Defensa");
        posiciones.add("Mediocampista");
        posiciones.add("Delantero");

        switch (action){

            case "guardar":
                Jugadores jugador1 = new Jugadores();
                jugador1.setNombre(request.getParameter("nombre"));
                jugador1.setEdad(Integer.parseInt(request.getParameter("edad")));
                jugador1.setPosicion(request.getParameter("posicion"));
                jugador1.setClub(request.getParameter("club"));
                jugador1.setSn_idSeleccion(Integer.parseInt(request.getParameter("sn_idSeleccion")));
                ArrayList<Jugadores> listaJugadores = daoJugadores.listarJugadores();
                boolean evaluar = false;
                for(Jugadores j: listaJugadores){
                    if(j.getNombre().equalsIgnoreCase(jugador1.getNombre())){
                        evaluar = true;
                        break;
                    }
                }

                if(!jugador1.getNombre().equalsIgnoreCase("")
                    && !request.getParameter("edad").equalsIgnoreCase("")
                        && !jugador1.getPosicion().equalsIgnoreCase("")
                            &&!jugador1.getClub().equalsIgnoreCase("")
                              && !request.getParameter("sn_idSeleccion").equalsIgnoreCase("")
                                && evaluar ==false){

                    daoJugadores.crearJugador(jugador1);
                }else{
                    request.setAttribute("posiciones", posiciones);
                    request.setAttribute("selecciones",daoSeleccionesNacionales.listarSelecciones());
                    view = request.getRequestDispatcher("/Jugadores/FormCreate.jsp");
                    view.forward(request, response);
                }
                response.sendRedirect(request.getContextPath()+"/JugadoresServlet");
                break;
            case "actualizar":
                Jugadores jugador2 = new Jugadores();
                jugador2.setIdJugadores(Integer.parseInt(request.getParameter("id")));
                jugador2.setNombre(request.getParameter("nombre"));
                jugador2.setEdad(Integer.parseInt(request.getParameter("edad")));
                jugador2.setPosicion(request.getParameter("posicion"));
                jugador2.setClub(request.getParameter("club"));
                jugador2.setSn_idSeleccion(Integer.parseInt(request.getParameter("sn_idSeleccion")));

                if(!jugador2.getNombre().equalsIgnoreCase("")
                        && !request.getParameter("edad").equalsIgnoreCase("")
                        && !jugador2.getPosicion().equalsIgnoreCase("")
                        &&!jugador2.getClub().equalsIgnoreCase("")
                        && !request.getParameter("sn_idSeleccion").equalsIgnoreCase("")){

                    daoJugadores.actualizarJugador(jugador2);
                }else{
                    request.setAttribute("posiciones", posiciones);
                    request.setAttribute("selecciones",daoSeleccionesNacionales.listarSelecciones());
                    request.setAttribute("jugador", jugador2);
                    view = request.getRequestDispatcher("/Jugadores/FormEdit.jsp");
                    view.forward(request, response);
                }
                response.sendRedirect(request.getContextPath()+"/JugadoresServlet");


                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoJugadores daoJugadores = new DaoJugadores();
        DaoSeleccionesNacionales daoSeleccionesNacionales = new DaoSeleccionesNacionales();
        RequestDispatcher view;
        ArrayList<String> posiciones = new ArrayList<>();
        posiciones.add("Portero");
        posiciones.add("Defensa");
        posiciones.add("Mediocampista");
        posiciones.add("Delantero");
        switch (action){
            case "lista":
                ArrayList<Jugadores> jugadoresArrayList = daoJugadores.listarJugadores();
                request.setAttribute("lista", jugadoresArrayList);
                view = request.getRequestDispatcher("/Jugadores/lista.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("posiciones", posiciones);
                request.setAttribute("selecciones",daoSeleccionesNacionales.listarSelecciones());
                view = request.getRequestDispatcher("/Jugadores/FormCreate.jsp");
                view.forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                Jugadores jugadores = daoJugadores.buscarJugadore(id);

                if(jugadores != null){
                    request.setAttribute("posiciones", posiciones);
                    request.setAttribute("selecciones",daoSeleccionesNacionales.listarSelecciones());
                    request.setAttribute("jugador", jugadores);
                    view = request.getRequestDispatcher("/Jugadores/FormEdit.jsp");
                    view.forward(request, response);
                }else{
                    response.sendRedirect(request.getContextPath()+"/JugadoresServlet");
                }
                break;
            case "borrar":
                int id2 = Integer.parseInt(request.getParameter("id"));
                if(daoJugadores.buscarJugadore(id2) != null){
                    daoJugadores.borrarJugador(id2);
                }
                response.sendRedirect(request.getContextPath() + "/JugadoresServlet");
                break;
        }
    }
}
