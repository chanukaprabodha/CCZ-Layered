package lk.ijse.ccz.dao.custom;

import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.entity.Inventory;
import lk.ijse.ccz.model.InventoryDTO;
import lk.ijse.ccz.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO extends CrudDAO<Inventory> {

    public  boolean updateQty(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException;

    public   boolean updateQty(OrderDetailDTO od) throws SQLException, ClassNotFoundException;

    public  double getUnitPrice(String recipe) throws SQLException, ClassNotFoundException;

    public  String getProductId(String cellData) throws SQLException, ClassNotFoundException;

}
