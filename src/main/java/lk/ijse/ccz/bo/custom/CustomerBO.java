package lk.ijse.ccz.bo.custom;

import lk.ijse.ccz.bo.SuperBO;
import lk.ijse.ccz.model.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    public  String findCustomer(String mobile) throws SQLException, ClassNotFoundException;

    public  char[] getCustomerCount() throws SQLException, ClassNotFoundException;

    public  String getCustomerName(String cusId) throws SQLException, ClassNotFoundException;

    public  String getCustomerEmail(String cusId) throws SQLException, ClassNotFoundException;
}
