<!DOCTYPE html>
<html>
<head>
    <title>Registrar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<div class="container mt-5">

<!--Creamos un formulario donde enviaremos todos los datos de un nuevo usuario-->

<!--Estos datos seran enviados a Resgistrar usuario Servlet-->

    <h1 class="text-center">Registrar Nuevo Usuario</h1>

    <form action="registrarUsuario" method="post" class="card p-4 shadow-lg">

<!--Obtenemos el por medio de un input tipo text el nombre del usuario-->
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>

<!--Obtenemos el por medio de un input tipo text el apellido del usuario-->
        <div class="mb-3">
            <label for="apellido" class="form-label">Apellido</label>
            <input type="text" class="form-control" id="apellido" name="apellido" required>
        </div>

<!--Obtenemos el por medio de un input tipo email el correo del usuario-->
        <div class="mb-3">
            <label for="correo" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="correo" name="correo" required>
        </div>

<!--Obtenemos el por medio de un input tipo password la contrasenia del usuario-->
        <div class="mb-3">
            <label for="clave" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="clave" name="clave" required>
        </div>

        <button type="submit" class="btn btn-success">Registrar</button>

        <a href="usuarios" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>