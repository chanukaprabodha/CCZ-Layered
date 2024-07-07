package lk.ijse.ccz.dao.custom;

import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {

    public  List<String> getIds() throws SQLException ;

    public  String findCustomer(String mobile) throws SQLException ;

    public  char[] getCustomerCount() throws SQLException ;

    public  String getCustomerName(String cusId) throws SQLException ;

    public  String getCustomerEmail(String cusId) throws SQLException ;
}
