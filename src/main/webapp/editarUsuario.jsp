<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!--Creamos un JSP donde vamos a poder modificar a todos los usuarios que hayamos registrado -->

<div class="container mt-5">

<!--Iniciamos un formulario donde retornaremos por cada input los datos del usuario registrado para poder modificarlo-->
    <h1 class="text-center">Editar Usuario</h1>

    <form action="editarUsuario" method="post" class="card p-4 shadow-lg">
    <!--En este input retornaremos lo que sería el codigo del usuario-->
        <input type="hidden" name="id" value="${usuario.id}">

    <!--Aqui retornaremos el NOMBRE, para luego poder cambiarlo por otro-->
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre}" required>
        </div>

    <!--Aqui retornaremos el APELLIDO, para luego poder cambiarlo por otro-->
        <div class="mb-3">
            <label for="apellido" class="form-label">Apellido</label>
            <input type="text" class="form-control" id="apellido" name="apellido" value="${usuario.apellido}" required>
        </div>

    <!--Aqui retornaremos el CORREO, para luego poder cambiarlo por otro-->
        <div class="mb-3">
            <label for="correo" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="correo" name="correo" value="${usuario.correo}" required>
        </div>

    <!--Aqui retornaremos la CLAVE, para luego poder cambiarlo por otra-->
        <div class="mb-3">
            <label for="clave" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="clave" name="clave" value="${usuario.clave}" required>
        </div>

    <!--terminaremos de guardar los cambios por medio del boton submit GUARDAR CAMBIOS-->
        <button type="submit" class="btn btn-primary">Guardar Cambios</button>

    <!--Y en caso que no queramos, abriremos un enlace que nos retornara a la lista de nuestros usuario-->
        <a href="usuarios" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>