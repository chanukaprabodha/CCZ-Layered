package lk.ijse.ccz.dao.custom.impl;

import lk.ijse.ccz.dao.SQLUtill;
import lk.ijse.ccz.dao.custom.CustomerDAO;
import lk.ijse.ccz.entity.Customer;
import lk.ijse.ccz.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("INSERT INTO customer VALUES(?, ?, ?, ?,?)", entity.getCustomerID(), entity.getName(), entity.getEmail(), entity.getAddress(), entity.getContact());
    }

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("UPDATE customer SET name = ?,email = ?, address = ?, phone = ? WHERE customerId = ?", entity.getName(), entity.getEmail(), entity.getAddress(), entity.getContact(), entity.getCustomerID());
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("DELETE FROM customer WHERE customerId = ?", id);
    }

    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomer = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM customer");
        while (rst.next()){
            Customer customer = new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
            allCustomer.add(customer);
        }
        return allCustomer;
    }

    public  String findCustomer(String mobile) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT customerId FROM customer WHERE phone = ?", mobile);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public  char[] getCustomerCount() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT COUNT(customerId) FROM customer");
        if (rst.next()){
            return rst.getString(1).toCharArray();
        }
        return null;
    }

    public  String getCustomerName(String cusId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT name FROM customer WHERE customerId = ?", cusId);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public  String getCustomerEmail(String cusId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT email FROM customer WHERE customerId = ?", cusId);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
