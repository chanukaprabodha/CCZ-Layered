package lk.ijse.ccz.dao.custom;

import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    public  boolean save1(ArrayList<OrderDetail> odList) throws SQLException, ClassNotFoundException;

}
