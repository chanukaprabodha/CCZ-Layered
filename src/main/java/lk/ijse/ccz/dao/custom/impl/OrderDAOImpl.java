package lk.ijse.ccz.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.ccz.dao.SQLUtill;
import lk.ijse.ccz.dao.custom.OrderDAO;
import lk.ijse.ccz.entity.OrderDetail;
import lk.ijse.ccz.entity.Orders;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    public String currentId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT orderId FROM orders ORDER BY CAST(SUBSTRING(orderId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            return rst.getString(1);
        }
        return "O0001";
//            String sql = "SELECT orderId FROM orders ORDER BY CAST(SUBSTRING(orderId, 2) AS UNSIGNED) DESC LIMIT 1";
//
//            Connection connection = DbConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement(sql);
//            ResultSet resultSet = pstm.executeQuery();
//
//            if(resultSet.next()) {
//                return resultSet.getString(1);
//            }
//            return null;
    }

    public int getUnitPrice(String recipe) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT price FROM ingredient WHERE ing_id = ?", recipe);
        if (rst.next()){
            return rst.getInt(1);
        }
        return 0;
//        String sql = "SELECT price FROM ingredient WHERE ing_name = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, recipe);
//        ResultSet resultSet = pstm.executeQuery();
//
//        if(resultSet.next()) {
//            return resultSet.getInt(1);
//        }
//        return 0;
    }

    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("INSERT INTO orders VALUES(?, ?, ?, ?)", entity.getOrderId(), entity.getOrderDate(), entity.getCustomerID(), entity.getTotalAmount());
//        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?)";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
//        pstm.setString(1, entity.getOrderId());
//        pstm.setDate(2, (Date) entity.getOrderDate());
//        pstm.setString(3, entity.getCustomerID());
//        pstm.setDouble(4, entity.getTotalAmount());
//
//        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public XYChart.Series incomeChart(XYChart.Series chart) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT orderDate, SUM(totalAmount) FROM orders GROUP BY orderDate ORDER BY TIMESTAMP(orderDate)");
        while (rst.next()) {
            chart.getData().add(new XYChart.Data<>(rst.getString(1), rst.getFloat(2)));
        }
        return chart;

//        String sql = "SELECT orderDate, SUM(totalAmount) FROM orders GROUP BY orderDate ORDER BY TIMESTAMP(orderDate)";
//
//        try {
//            ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
//
//            while (resultSet.next()) {
//                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getFloat(2)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return chart;
    }

    public XYChart.Series customerChart(XYChart.Series chart) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT orderDate, COUNT(orderId) FROM orders GROUP BY orderDate ORDER BY TIMESTAMP(orderDate)");
        while (rst.next()) {
            chart.getData().add(new XYChart.Data<>(rst.getString(1), rst.getInt(2)));
        }
        return chart;
//        String sql = "SELECT orderDate, COUNT(orderId) FROM orders GROUP BY orderDate ORDER BY TIMESTAMP(orderDate)";
//
//        try {
//            ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
//
//            while (resultSet.next()) {
//                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return chart;
    }

    public double getTodayIncome(Date sqlDate) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT SUM(totalAmount) FROM orders WHERE orderDate = ?", sqlDate);
        if (rst.next()) {
            return rst.getDouble(1);
        }
        return 0;

//        String sql = "SELECT SUM(totalAmount) FROM orders WHERE orderDate = ?";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setDate(1, sqlDate);
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            return resultSet.getDouble(1);
//        }
//        return 0;
    }

    public double getTotalIncome() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT SUM(totalAmount) FROM orders");
        if (rst.next()) {
            return rst.getDouble(1);
        }
        return 0;

//        String sql = "SELECT SUM(totalAmount) FROM orders";
//
//        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
//
//        if(resultSet.next()) {
//            return resultSet.getDouble(1);
//        }
//        return 0;
    }

    public int getTodayOrder(Date sqlDate) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT COUNT(orderId) FROM orders WHERE orderDate = ?", sqlDate);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;

//        String sql = "SELECT COUNT(orderId) FROM orders WHERE orderDate = ?";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setDate(1, sqlDate);
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            return resultSet.getInt(1);
//        }
//        return 0;
    }

    public String getMostRecentOrderId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("select orderId from orders order by orderId desc limit 1");
        if (rst.next()){
            return rst.getString(1);
        }
        return null ;
//        String sql = "select orderId from orders order by orderId desc limit 1";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()){
//            return resultSet.getString(1);
//        }
//        return null ;
    }
}
