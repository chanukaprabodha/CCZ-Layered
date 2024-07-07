package lk.ijse.ccz.dao.custom.impl;

import lk.ijse.ccz.dao.SQLUtill;
import lk.ijse.ccz.dao.custom.CustomerDAO;
import lk.ijse.ccz.db.DbConnection;
import lk.ijse.ccz.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("INSERT INTO customer VALUES(?, ?, ?, ?,?)", customer);
    }

    public boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name = ?,email = ?, address = ?, phone = ? WHERE Customerid = ?";

        try (PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setObject(1, customer.getName());
            pstm.setObject(2, customer.getEmail());
            pstm.setObject(3, customer.getAddress());
            pstm.setObject(4, customer.getContact());
            pstm.setObject(5, customer.getCustomerID());

            return pstm.executeUpdate() > 0;
        }

    }

    public  boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE Customerid = ?";

        try (PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setObject(1, id);

            return pstm.executeUpdate() > 0;
        }
    }

    public  List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> customersList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String address = resultSet.getString(4);
            String contact = resultSet.getString(5);

            Customer customer = new Customer(id, name, email , address, contact);
            customersList.add(customer);
        }
        return customersList;
    }

    public  List<String> getIds() throws SQLException {

        String sql = "SELECT customerId FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public  String findCustomer(String mobile) throws SQLException {

        String sql = "SELECT customerID FROM customer WHERE phone = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, mobile);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public  char[] getCustomerCount() throws SQLException {

        String sql = "SELECT COUNT(*) FROM customer";

        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1).toCharArray();
        }
        return null;
    }

    public  String getCustomerName(String cusId) throws SQLException {
        String sql = "SELECT name FROM customer WHERE customerId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, cusId);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        }

        return null;
    }

    public  String getCustomerEmail(String cusId) throws SQLException {
        String sql = "SELECT email FROM customer WHERE customerId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, cusId);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}