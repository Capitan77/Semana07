package com.cibertec.servlets;

import com.cibertec.dao.UsuarioDao;
import com.cibertec.dao.impl.UsuarioDaoImpl;
import com.cibertec.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrarUsuario", urlPatterns = "/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {
    private final UsuarioDao usuarioDAO = new UsuarioDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtenemos sesion actual
        HttpSession session = request.getSession(false);
        //si es igual a null o sesion que se obtiene de usuario es null
        if (session == null || session.getAttribute("usuario") == null) {
            //retorna al Login.jsp
            response.sendRedirect("login.jsp");
            return;
        }

        //Obtendremos todos los datos ingresados desde el formulario de registroUsuario.jsp
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        try {
            //y los almacenaremos en un nuevo Objeto de la Clase Usuario
            Usuario usuario = new Usuario(nombre, apellido, correo, clave);
            //LLamaremos al metodo registrarUsuario por medio de la variable usuarioDAO
            usuarioDAO.registrarUsuario(usuario);
            //y nos retornara al servlet usuarios o Listado Usuarios Servlet
            response.sendRedirect("usuarios");
        } catch (SQLException e) {
            throw new ServletException("Error al registrar usuario", e);
        }
    }
}
