package com.cibertec.servlets;

import com.cibertec.dao.UsuarioDao;
import com.cibertec.dao.impl.UsuarioDaoImpl;
import com.cibertec.models.Usuario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "c", urlPatterns = "/usuarios")
public class ListadoUsuariosServlet extends HttpServlet {
    //Iniciaremos una variable que provenga de la interfaz USUARIODAO para poder llamar a un metodo de UsuarioDaoImpl
    private final UsuarioDao usuarioDAO = new UsuarioDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtenemos sesion actual
        HttpSession session = request.getSession(false);
        //si es igual a null o sesion que se obtiene de usuario es null
        if (session == null || session.getAttribute("usuario") == null) {
            //nos hara retornar al login JSP
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            //llamaremos al metodo listarUsuarios por medio de la variables usuarioDAO
            // y lo almacenaremos en una lista de la clase Usuario
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();
            //la lista se almacenara en una variable  que sera enviado a JSP de usuarios.jsp
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al listar usuarios", e);
        }
    }
}