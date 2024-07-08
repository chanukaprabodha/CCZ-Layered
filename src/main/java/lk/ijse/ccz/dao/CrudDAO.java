package lk.ijse.ccz.dao;

import lk.ijse.ccz.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {

    public  boolean save(T entity) throws SQLException, ClassNotFoundException;

    public  boolean update(T entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

}
