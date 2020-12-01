<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.SeleccionesNacionales" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<SeleccionesNacionales> lista = (ArrayList<SeleccionesNacionales>) request.getAttribute("lista");
        %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
    <title>JSP Page</title>
</head>
<body>
<div class='container'>
    <jsp:include page="includes/navbar.jsp"/>
    <div class="row mb-5 mt-4">
        <div class="col-lg-6">
            <h1 class=''>Lista de Selecciones Nacionales</h1>
        </div>
        <div class="col-lg-6 my-auto text-lg-right">
            <a href="<%= request.getContextPath()%>/SeleccionesServlet?action=crear" class="btn btn-primary">Crear Seleccion</a>
        </div>
    </div>
    <table class="table">
        <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Director Técnico</th>
            <th>Estadio</th>
            <th></th>
            <th></th>
        </tr>
        <%
            int i = 1;
            for (SeleccionesNacionales sn : lista) {
        %>
        <tr>
            <td><%=i%></td>
            <td><%=sn.getNombre()%></td>
            <td><%=sn.getTecnico()%></td>
            <td><%=sn.getEstadios_idEstadio()%></td>
            <td>
                <a href="<%=request.getContextPath()%>/SeleccionesServlet?action=editar&id=<%=sn.getIdSeleccionesNacionales()%>">
                    Editar
                </a>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/SeleccionesServlet?action=borrar&id=<%=sn.getIdSeleccionesNacionales()%>">
                    Borrar
                </a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>




</body>
</html>
