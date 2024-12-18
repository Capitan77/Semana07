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

@WebServlet(name = "EliminarUsuario", urlPatterns = "/eliminarUsuario")
public class EliminarUsuarioServlet extends HttpServlet {

    //Iniciaremos una variable que provenga de la interfaz USUARIODAO para poder llamar a un metodo de UsuarioDaoImpl
    private final UsuarioDao usuarioDAO = new UsuarioDaoImpl();


    //Abrimos un metodo GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtenemos sesion actual
        HttpSession session = request.getSession(false);
        //si es igual a null o sesion que se obtiene de usuario es null
        if (session == null || session.getAttribute("usuario") == null) {
            //retorna al Login.jsp
            response.sendRedirect("login.jsp");
            return;
        }

        //creamos una variable INT que almacene nuestro ID que usamos como condicion o parametro
        int id = Integer.parseInt(request.getParameter("id"));


        //Llamaremos a eliminarUsuario por medio de la variable usuarioDAO
        try {
            usuarioDAO.eliminarUsuario(id);
            //y lo enviarios hacia el servlet ListadoUsuarios o Usuarios
            response.sendRedirect("usuarios");
        } catch (SQLException e) {
            throw new ServletException("Error al eliminar usuario", e);
        }
    }
}