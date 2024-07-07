package lk.ijse.ccz.dao.custom;

import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.model.ConfirmOrder;

import java.sql.SQLException;

public interface ConfirmOrderDAO extends CrudDAO<CustomerDAO> {
    public  boolean placeOrder(ConfirmOrder po) throws SQLException ;
}
