package lk.ijse.ccz.dao.custom;

import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.model.Inventory;
import lk.ijse.ccz.model.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO extends CrudDAO<Inventory> {

    public  boolean updateQty(List<OrderDetail> odList) throws SQLException ;

    public   boolean updateQty(OrderDetail od) throws SQLException ;

    public  double getUnitPrice(String recipe) throws SQLException ;

    public  String getProductId(String cellData) throws SQLException ;
}
