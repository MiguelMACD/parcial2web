package co.empresa.proyectoWeb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.proyectoWeb.modelo.Movimiento;
import co.empresa.proyectoWeb.util.Conexion;

public class MovimientoDao {

    private Conexion conexion;

    private static final String INSERT_MOVIMIENTO_SQL = "INSERT INTO bill (date_bill, user_id, value, type, observation) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_MOVIMIENTO_SQL = "DELETE FROM bill WHERE id=?;";
    private static final String UPDATE_MOVIMIENTO_SQL = "UPDATE bill SET date_bill = ?, user_id = ?, value = ?, type = ?, observation = ? WHERE id = ?;";
    private static final String SELECT_MOVIMIENTO_BY_ID = "SELECT * FROM bill WHERE id = ?;";
    private static final String SELECT_ALL_MOVIMIENTO = "SELECT * FROM bill;";

    public MovimientoDao() {
        this.conexion = Conexion.getConexion();
    }

    public void insert(Movimiento movimiento) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_MOVIMIENTO_SQL);
            preparedStatement.setDate(1, movimiento.getDateBill());
            preparedStatement.setInt(2, movimiento.getUserId());
            preparedStatement.setBigDecimal(3, movimiento.getValue());
            preparedStatement.setInt(4, movimiento.getType());
            preparedStatement.setString(5, movimiento.getObservation());
            conexion.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_MOVIMIENTO_SQL);
            preparedStatement.setInt(1, id);
            conexion.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Movimiento movimiento) throws SQLException {
        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_MOVIMIENTO_SQL);
            preparedStatement.setDate(1, movimiento.getDateBill());
            preparedStatement.setInt(2, movimiento.getUserId());
            preparedStatement.setBigDecimal(3, movimiento.getValue());
            preparedStatement.setInt(4, movimiento.getType());
            preparedStatement.setString(5, movimiento.getObservation());
            preparedStatement.setInt(6, movimiento.getId());
            conexion.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movimiento> selectAll() {
        List<Movimiento> movimientos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_MOVIMIENTO);
            ResultSet rs = conexion.query();

            while (rs.next()) {
                int id = rs.getInt("id");
                java.sql.Date dateBill = rs.getDate("date_bill");
                int userId = rs.getInt("user_id");
                java.math.BigDecimal value = rs.getBigDecimal("value");
                int type = rs.getInt("type");
                String observation = rs.getString("observation");
                movimientos.add(new Movimiento(id, dateBill, userId, value, type, observation));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimientos;
    }

    public Movimiento select(int id) {
        Movimiento movimiento = null;

        try {
            PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_MOVIMIENTO_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = conexion.query();

            while (rs.next()) {
                java.sql.Date dateBill = rs.getDate("date_bill");
                int userId = rs.getInt("user_id");
                java.math.BigDecimal value = rs.getBigDecimal("value");
                int type = rs.getInt("type");
                String observation = rs.getString("observation");
                movimiento = new Movimiento(id, dateBill, userId, value, type, observation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimiento;
    }

   
}
