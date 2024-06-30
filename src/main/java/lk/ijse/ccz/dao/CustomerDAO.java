package lk.ijse.ccz.dao;

import lk.ijse.ccz.db.DbConnection;
import lk.ijse.ccz.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {
    public  boolean save(Customer customer) throws SQLException ;

    public  boolean update(Customer customer) throws SQLException ;

    public  boolean delete(String id) throws SQLException ;

    public  List<Customer> getAll() throws SQLException ;

    public  List<String> getIds() throws SQLException ;

    public  String findCustomer(String mobile) throws SQLException ;

    public  char[] getCustomerCount() throws SQLException ;

    public  String getCustomerName(String cusId) throws SQLException ;

    public  String getCustomerEmail(String cusId) throws SQLException ;
}
