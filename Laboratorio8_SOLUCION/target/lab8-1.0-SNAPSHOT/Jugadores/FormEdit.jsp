<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.SeleccionesNacionales" %>
<%@ page import="Bean.Estadios" %>
<%@ page import="Bean.Jugadores" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<String> posiciones = (ArrayList<String>) request.getAttribute("posiciones");
    ArrayList<SeleccionesNacionales> lista = (ArrayList<SeleccionesNacionales>) request.getAttribute("selecciones");
    Jugadores jugador = (Jugadores) request.getAttribute("jugador");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
    <title>JSP Page</title>
</head>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp"/>
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Editar un Jugador de fútbol</h1>
            <form method="POST" action="<%=request.getContextPath()%>/JugadoresServlet?action=actualizar">
                <input type="hidden" class="form-control" name="id" value="<%=jugador.getIdJugadores()%>">
                <div class="form-group">
                    <label >Nombre</label>
                    <input type="text" class="form-control" name="nombre" value="<%=jugador.getNombre()%>">
                </div>
                <div class="form-group">
                    <label >Edad</label>
                    <input type="text" class="form-control" name="edad" value="<%=jugador.getEdad()%>">
                </div>
                <div class="form-group">
                    <label >Posición</label>
                    <select name="posicion" class="form-control">
                        <% for (String p: posiciones) {%>
                        <option value="<%=p%>" <%=p.equalsIgnoreCase(jugador.getPosicion())?"selected" : ""%>><%=p%></option>
                        <% }%>
                    </select>
                </div>
                <div class="form-group">
                    <label >Club</label>
                    <input type="text" class="form-control" name="club" value="<%=jugador.getClub()%>">
                </div>
                <div class="form-group">
                    <label >Seleccion Nacional</label>
                    <select name="sn_idSeleccion" class="form-control">
                        <% for (SeleccionesNacionales s: lista) {%>
                        <option value="<%=s.getIdSeleccionesNacionales()%>" <%=s.getIdSeleccionesNacionales() ==
                        jugador.getSn_idSeleccion() ? "selected":"" %> ><%=s.getNombre()%></option>
                        <% }%>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="<%= request.getContextPath()%>/JugadoresServlet" class="btn btn-danger">Cancelar</a>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
