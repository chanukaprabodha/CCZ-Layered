package lk.ijse.ccz.bo.custom;

import lk.ijse.ccz.bo.SuperBO;
import lk.ijse.ccz.entity.Inventory;
import lk.ijse.ccz.model.InventoryDTO;
import lk.ijse.ccz.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InventoryBO extends SuperBO {

    public  boolean save(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public  boolean update(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException;

    public List<InventoryDTO> getAll() throws SQLException, ClassNotFoundException;

    public  boolean updateQty(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException;

    public   boolean updateQty(OrderDetailDTO od) throws SQLException, ClassNotFoundException;

    public  double getUnitPrice(String recipe) throws SQLException, ClassNotFoundException;

    public  String getProductId(String cellData) throws SQLException, ClassNotFoundException;
}
