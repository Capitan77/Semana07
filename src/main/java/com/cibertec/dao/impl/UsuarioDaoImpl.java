package com.cibertec.dao.impl;

import com.cibertec.dao.UsuarioDao;
import com.cibertec.models.Usuario;
import com.cibertec.repository.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//Creamos una clase JAVA que IMPLEMENTE todos los metodo creados en USUARIODAO
public class UsuarioDaoImpl implements UsuarioDao {

    //LISTRAUSUARIOS
    @Override
    public List<Usuario> listarUsuarios() throws SQLException {

        //Creamos una lista vacia donde se almacenaran todos los usuarios que obtengamos del SQL
        List<Usuario> usuarios = new ArrayList<>();
        //Amacenamos en una cadena de texto la consulta SELECT
        String query = "SELECT * FROM Usuario";

        //Establecemos la conexion momentanea
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            //ejecutamos un bucle donde retorne todos los datos que encuentre
            while (rs.next()) {
                //seran agregados a la Lista Usuario creando un nuevo objeto llamado Usuario
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("clave")
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //retornara la lista con todos los usuarios
        return usuarios;
    }


    //VALIDARUSUARIO:
    @Override
    public Usuario validarUsuario(String correo, String clave) throws SQLException {
        //alamacenaremos la consulta consultando un usuario que coincida con el correo y clave registrados
        String query = "SELECT * FROM Usuario WHERE correo = ? AND clave = ?";
        //establecemos conexion momentanea
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            //preparamos y establecemos los parametros
            //WHERE (correo=?) -> 1 AND (Clave=?) -> 2 (los numeros son las posiciones despues del where)
            ps.setString(1, correo);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();

            //si existe un usuario que coincida con el CORREO y la CLAVE de los parametros
            //se creara un nuevo usuario retornando los datos del usuario que coincidieron
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("clave")
                );
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //en caso contrario se retorna NULL
        return null;
    }


    //REGISTRAR USUARIO
    @Override
    public void registrarUsuario(Usuario usuario) throws SQLException {
        //Almacenamos consulta en una cadena - sobre un INSERT para un nuevo USUARIO
        String query = "INSERT INTO Usuario (nombre, apellido, correo, clave) VALUES (?, ?, ?, ?)";

        //Establecemos conexion
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            //Y posiciones de los parametros, coincidiendo con los ? y las columnas de la tabla SQL
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getClave());
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    //OBTENERUSUARIOPORID
    @Override
    public Usuario obtenerUsuarioPorId(int id) throws SQLException {
        //Almacenamos la consulta en una cadena, donde tendremos como condicion el ID
        String query = "SELECT * FROM Usuario WHERE id = ?";
        //Realizamos la conexion momentanea
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            //establecemos la posicion de nuestra condicion segun las columnas de nuestra tabla SQL
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                //Si existe y coincide con nuestro ID se retornara todos los datos del usuario
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getString("clave")
                    );
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return null; // Retorna null si no encuentra el usuario
    }

    //EDITARUSUARIO
    @Override
    public void editarUsuario(Usuario usuario) throws SQLException {
        //Almacenamos consulta en una cadena - para poder ACTUALIZAR el usuario seleccionado por medio del ID
        String query = "UPDATE Usuario SET nombre = ?, apellido = ?, correo = ?, clave = ? WHERE id = ?";
        //establecemos conexion momentanea
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            //establecemos las posiciones de nuestros datos para que coincidan con los ?
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //ELIMINARUSUARIO
    @Override
    public void eliminarUsuario(int id) throws SQLException {
        //Almacenamos la consulta en un STRING - donde nuestro parametro o condicion sera el ID
        String query = "DELETE FROM Usuario WHERE id = ?";
        //Establecemos la conexion momentanea
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            //alineamos nuestro id con la posicion de columna del ?
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}