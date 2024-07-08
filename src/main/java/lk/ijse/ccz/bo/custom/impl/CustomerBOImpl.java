package lk.ijse.ccz.bo.custom.impl;

import lk.ijse.ccz.bo.BOFactory;
import lk.ijse.ccz.bo.custom.CustomerBO;
import lk.ijse.ccz.dao.DAOFactory;
import lk.ijse.ccz.dao.custom.CustomerDAO;
import lk.ijse.ccz.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.ccz.entity.Customer;
import lk.ijse.ccz.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCustomerID(),customerDTO.getName(),customerDTO.getEmail(),customerDTO.getAddress(),customerDTO.getContact()));

    }

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustomerID(),customerDTO.getName(),customerDTO.getEmail(),customerDTO.getAddress(),customerDTO.getContact()));
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCustomerID(),c.getName(),c.getEmail(),c.getAddress(),c.getContact()));
        }
        return allCustomers;
    }

    public  String findCustomer(String mobile) throws SQLException, ClassNotFoundException {

        return customerDAO.findCustomer(mobile);
    }

    public  char[] getCustomerCount() throws SQLException, ClassNotFoundException {

        return customerDAO.getCustomerCount();
    }

    public  String getCustomerName(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerName(cusId);
    }

    public  String getCustomerEmail(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerEmail(cusId);
    }
}
