package lk.ijse.ccz.dao;

import lk.ijse.ccz.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public  boolean save(T entity) throws SQLException, ClassNotFoundException;

    public  boolean update(T entity) throws SQLException ;

    public  boolean delete(String id) throws SQLException ;

    public List<T> getAll() throws SQLException ;

}
