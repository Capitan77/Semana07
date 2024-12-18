package com.cibertec.dao;

import com.cibertec.models.Usuario;
import java.sql.SQLException;
import java.util.List;


//Crearemos una Interfaz donde nombraremos 6 metodos
public interface UsuarioDao {

//LISTARUSUARIOS: permitira generar una Lista de la clase Usuario para almacenar los Usuarios de SQLServer
    List<Usuario> listarUsuarios() throws SQLException;

//OBTENERUSUARIOPORID: nos va a permitir explorar la clase Usuario, utilizando el ID del usuario que queremos retornar
    Usuario obtenerUsuarioPorId(int id) throws SQLException;

//VALIDARUSUARIO: nos permite explorar la clase Usuario por medio de un CORREO y CLAVE; servira para validar la Sesion
    Usuario validarUsuario(String correo, String clave) throws SQLException;

//REGISTRARUSUARIO: nos permite insertar un nuevo usuario a la clase Usuario
    void registrarUsuario(Usuario usuario) throws SQLException;

//EDITARUSUARIO: permite retornar un usuario registrado, modificar sus datos y guardar los cambios
    void editarUsuario(Usuario usuario) throws SQLException;

//ELIMINARUSUARIO: permite eliminar algun usuario por medio de su ID
    void eliminarUsuario(int id) throws SQLException;
}