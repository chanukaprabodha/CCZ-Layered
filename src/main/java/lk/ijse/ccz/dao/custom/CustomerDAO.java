package lk.ijse.ccz.dao.custom;

import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.entity.Customer;
import lk.ijse.ccz.model.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {

    public  String findCustomer(String mobile) throws SQLException, ClassNotFoundException;

    public  char[] getCustomerCount() throws SQLException, ClassNotFoundException;

    public  String getCustomerName(String cusId) throws SQLException, ClassNotFoundException;

    public  String getCustomerEmail(String cusId) throws SQLException, ClassNotFoundException;
}
