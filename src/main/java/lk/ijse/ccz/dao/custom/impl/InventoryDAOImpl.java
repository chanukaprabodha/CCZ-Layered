package lk.ijse.ccz.dao.custom.impl;

import lk.ijse.ccz.dao.SQLUtill;
import lk.ijse.ccz.dao.custom.InventoryDAO;
import lk.ijse.ccz.entity.Inventory;
import lk.ijse.ccz.dto.OrderDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {

    public  boolean save(Inventory entity) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("INSERT INTO ingredient VALUES(?, ?, ?, ?)", entity.getId(), entity.getName(), entity.getStock(), entity.getPrice());
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("DELETE FROM ingredient WHERE ing_id = ?", id);
    }

    public  boolean update(Inventory entity) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("UPDATE ingredient SET name = ?, stock = ?, price = ? WHERE ing_id = ?", entity.getName(), entity.getStock(), entity.getPrice(), entity.getId());
    }

    public ArrayList<Inventory> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> allInventories = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM ingredient");
        while (rst.next()){
            Inventory inventory = new Inventory(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getDouble(4));
            allInventories.add(inventory);
        }
        return allInventories;
    }

    public  boolean updateQty(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO od : odList) {
            if(!updateQty(od)) {
                return false;
            }
        }
        return true;
    }

    public   boolean updateQty(OrderDetailDTO od) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("UPDATE ingredient SET stock = stock - ? WHERE ing_id = ?", od.getIngredientQty(), od.getIngredientId());
//        String sql = "UPDATE ingredient SET stock = stock - ? WHERE ing_id = ?";
//        try (PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setDouble(1, od.getIngredientQty());
//            pstm.setString(2, od.getIngredientId());
//
//            return pstm.executeUpdate() > 0;
//        }

    }

    public  double getUnitPrice(String recipe) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT price FROM ingredient WHERE ing_id = ?", recipe);
        if (rst.next()){
            return rst.getDouble(1);
        }
        return 0;
//        String sql = "SELECT price FROM ingredient WHERE ing_id = ?";
//
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
//        pstm.setString(1, recipe);
//        ResultSet resultSet = pstm.executeQuery();
//
//        if (resultSet.next()) {
//            return resultSet.getDouble(1);
//        }
//        return 0;
    }

    public  String getProductId(String cellData) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT ing_id FROM ingredient WHERE ing_name = ?", cellData);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
//        String sql = "SELECT ing_id FROM ingredient WHERE ing_name = ?";
//
//        System.out.println(cellData);
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
//        pstm.setString(1, cellData);
//        ResultSet resultSet = pstm.executeQuery();
//
//
//        if (resultSet.next()) {
//            return resultSet.getString(1);
//        }
//        return null;
    }

}
