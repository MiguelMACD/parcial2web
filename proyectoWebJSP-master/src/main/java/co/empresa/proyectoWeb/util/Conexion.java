package co.empresa.proyectoWeb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private Connection con = null;
    private static Conexion db;
    private PreparedStatement preparedStatement;

    // Credenciales del servidor PostgreSQL proporcionadas
    private static final String url = "jdbc:postgresql://fanny.db.elephantsql.com:5432/";
    private static final String dbName = "jnvgnqqv";
    private static final String driver = "org.postgresql.Driver";
    private static final String userName = "jnvgnqqv";
    private static final String password = "aTo0Yykrx9nCmRavmYFsikv_usQtfOen";

    public Conexion() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url + dbName, userName, password);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getConexion() {
        if (db == null) {
            db = new Conexion();
        }
        return db;
    }

    public ResultSet query() throws SQLException {
        ResultSet res = preparedStatement.executeQuery();
        return res;
    }

    public int execute() throws SQLException {
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public Connection getCon() {
        return this.con;
    }

    public PreparedStatement setPreparedStatement(String sql) throws SQLException {
        this.preparedStatement = con.prepareStatement(sql);
        return this.preparedStatement;
    }
}
