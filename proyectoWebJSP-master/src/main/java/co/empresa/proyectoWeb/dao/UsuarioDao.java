package co.empresa.proyectoWeb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.proyectoWeb.modelo.Usuario;
import co.empresa.proyectoWeb.util.Conexion;

public class UsuarioDao {

    private Conexion conexion;

    private static final String INSERT_USUARIO_SQL = "INSERT INTO users (username, pass, email) VALUES (?, ?, ?);";
    private static final String DELETE_USUARIO_SQL = "DELETE FROM users WHERE id=?;";
    private static final String UPDATE_USUARIO_SQL = "UPDATE users SET username = ?, pass = ?, email = ? WHERE id = ?;";
    private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String SELECT_ALL_USUARIO = "SELECT * FROM users;";

    public UsuarioDao() {
        this.conexion = Conexion.getConexion();
    }

    public void insert(Usuario usuario) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_USUARIO_SQL);
            preparedStatement.setString(1, usuario.getUsername());
            preparedStatement.setString(2, usuario.getPass());
            preparedStatement.setString(3, usuario.getEmail());
            conexion.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_USUARIO_SQL);
            preparedStatement.setInt(1, id);
            conexion.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Usuario usuario) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
            preparedStatement.setString(1, usuario.getUsername());
            preparedStatement.setString(2, usuario.getPass());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setInt(4, usuario.getId());
            conexion.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> selectAll() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_USUARIO);
            ResultSet rs = conexion.query();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String pass = rs.getString("pass");
                String email = rs.getString("email");
                usuarios.add(new Usuario(id, username, pass, email));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario select(int id) {
        Usuario usuario = null;

        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = conexion.query();

            while (rs.next()) {
                String username = rs.getString("username");
                String pass = rs.getString("pass");
                String email = rs.getString("email");
                usuario = new Usuario(id, username, pass, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

 
}
