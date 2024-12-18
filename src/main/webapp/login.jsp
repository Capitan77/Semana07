<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
<!--Tenemos el formulario para iniciar sesion-->

    <h1 class="text-center">Inicio de Sesión</h1>

<!--retorna un mensaje de error en caso ocurra un error al escribir el correo y contraseña-->

<!--La validacion del error vendra del servlet ValidarSesionServlet-->

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

<!--creamos un form que sera enviado a ValidarSesion y sera de tipo POST porque se trata de un formulario con datos personales-->

    <form action="ValidarSesion" method="post" class="card p-4 shadow-lg">


<!--obtenemos por medio de un input tipo email el correo -->

        <div class="mb-3">
            <label for="correo" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="correo" name="correo" required>
        </div>

<!--obtenemos por medio de un input tipo password la contraseña -->

        <div class="mb-3">
            <label for="clave" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="clave" name="clave" required>
        </div>

<!--enviamos el formulario con un boton submit-->

        <button type="submit" class="btn btn-primary">Iniciar Sesión</button>

    </form>
</div>
</body>
</html>