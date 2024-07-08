package lk.ijse.ccz.dao.custom;

import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.dao.SuperDAO;
import lk.ijse.ccz.entity.OrderDetail;
import lk.ijse.ccz.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    public  boolean save(ArrayList<OrderDetail> odList) throws SQLException, ClassNotFoundException;

}
