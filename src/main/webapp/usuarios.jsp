<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!--Creamos una lista donde retornaremos todos los usuarios que se hayan registrado-->
<div class="container mt-5">
    <h1 class="text-center">Usuarios Registrados</h1>

<!--En caso deseemos registrar un nuevo usuario, este enlace nos ayudara para poder hacerlo-->
    <a href="registroUsuario.jsp" class="btn btn-primary mb-3">Registrar Nuevo Usuario</a>


<!--Abrimos una tabla donde retornaremos todos los datos de nuestros usuarios-->
    <table class="table table-bordered table-hover">

    <!--Iniciamos la cabecera con los campos necesario teniendo los nombres de los datos de nuestro usuario-->
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Correo</th>
            <th>Acciones</th>
        </tr>
        </thead>

    <!--Iniciamos el cuerpo de nuestra tabla retornando los datos de los usuarios para cada campo-->
        <tbody>

        <!--Usamos una etiqueta personalizada  (taglib) que funcionara como un for-->
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.id}</td>
                <td>${usuario.nombre}</td>
                <td>${usuario.apellido}</td>
                <td>${usuario.correo}</td>
                <td>


                <!--Tendremos lo que serian los botones para EDITAR y ELIMINAR-->
                    <a href="editarUsuario?id=${usuario.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="eliminarUsuario?id=${usuario.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('¿Estás seguro de eliminar este usuario?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>