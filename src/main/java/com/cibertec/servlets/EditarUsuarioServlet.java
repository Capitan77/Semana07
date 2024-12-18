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

@WebServlet(name = "EditarUsuario", urlPatterns = "/editarUsuario")
public class EditarUsuarioServlet extends HttpServlet {
    private final UsuarioDao usuarioDAO = new UsuarioDaoImpl();


    //crearemos un metodo tipo GET
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

        try {
            //Llamaremos a obtenerUsuarioPorId por medio de la variable usuarioDAO
            //y obtendremos el usuario que hemos solicitado por nuestro ID
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
            //Si es diferente a null
            if (usuario != null) {
                //Se obtendran atributos
                request.setAttribute("usuario", usuario);
                //y se enviaran al editar usuario JSP
                request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
            } else {
                //En caso de que el ID sea incorrecto no devolvera al SERVLET usuarios o Listar usuarios
                response.sendRedirect("usuarios");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al cargar usuario", e);
        }
    }

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

        //creamos una variable INT que almacene nuestro ID que usamos como condicion o parametro
        int id = Integer.parseInt(request.getParameter("id"));

        //obtendremos todos los datos del usuario seleccionado y se almacenaran en cadenas
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        try {
            //se creara un nuevo usuario usando todos las variables cadenas
            Usuario usuario = new Usuario(id, nombre, apellido, correo, clave);
            //Llamaremos a editarUsuario por medio de la variable usuarioDAO, enviando el nuevo usuario que hemos creado
            usuarioDAO.editarUsuario(usuario);
            //y finalizaremos reotrnando al SERVLET usuarios
            response.sendRedirect("usuarios");
        } catch (SQLException e) {
            throw new ServletException("Error al editar usuario", e);
        }
    }
}