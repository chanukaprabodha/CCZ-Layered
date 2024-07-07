package lk.ijse.ccz.dao.custom.impl;

import lk.ijse.ccz.dao.custom.ConfirmOrderDAO;
import lk.ijse.ccz.dao.custom.InventoryDAO;
import lk.ijse.ccz.dao.custom.OrderDAO;
import lk.ijse.ccz.dao.custom.OrderDetailDAO;
import lk.ijse.ccz.db.DbConnection;
import lk.ijse.ccz.model.ConfirmOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class ConfirmOrderImpl implements ConfirmOrderDAO {
    
    OrderDAO orderDAO = new OrderDAOImpl();
    InventoryDAO inventoryDAO = new InventoryDAOImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    public  boolean placeOrder(ConfirmOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.save(po.getOrder());

            if (isOrderSaved) {

                boolean isOrderDetailSaved = orderDetailDAO.save(po.getOdList());

                if (isOrderDetailSaved) {

                    boolean isItemQtyUpdate = inventoryDAO.updateQty(po.getOdList());

                    if (isItemQtyUpdate) {

                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
