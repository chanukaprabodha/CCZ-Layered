package lk.ijse.ccz.dao.custom.impl;

import lk.ijse.ccz.dao.SQLUtill;
import lk.ijse.ccz.dao.custom.OrderDetailDAO;
import lk.ijse.ccz.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public  boolean save1(ArrayList<OrderDetail> odList) throws SQLException, ClassNotFoundException {
            for (OrderDetail od : odList) {
                if(!save(od)) {
                    return false;
                }
            }
            return true;
    }

    public   boolean save(OrderDetail od) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("INSERT INTO orderdetail VALUES(?, ?, ?, ?)", od.getOrderId(), od.getIngredientId(), od.getIngredientQty(), od.getUnitPrice());
//        String sql = "INSERT INTO orderdetail VALUES(?, ?, ?, ?)";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//        pstm.setString(1, od.getOrderId());
//        pstm.setString(2, od.getIngredientId());
//        pstm.setInt(3, (int) od.getIngredientQty());
//        pstm.setDouble(4, od.getUnitPrice());
//
//        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
