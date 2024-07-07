package lk.ijse.ccz.dao.custom.impl;

import lk.ijse.ccz.dao.custom.OrderDetailDAO;
import lk.ijse.ccz.db.DbConnection;
import lk.ijse.ccz.model.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public  boolean save(List<OrderDetail> odList) throws SQLException {
            for (OrderDetail od : odList) {
                if(!save(od)) {
                    return false;
                }
            }
            return true;
    }

    public   boolean save(OrderDetail od) throws SQLException {
        String sql = "INSERT INTO orderdetail VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1, od.getOrderId());
        pstm.setString(2, od.getIngredientId());
        pstm.setInt(3, (int) od.getIngredientQty());
        pstm.setDouble(4, od.getUnitPrice());

        return pstm.executeUpdate() > 0;
    }
}
