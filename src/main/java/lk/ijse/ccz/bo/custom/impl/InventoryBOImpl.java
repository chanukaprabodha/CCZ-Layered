package lk.ijse.ccz.bo.custom.impl;

import lk.ijse.ccz.bo.custom.InventoryBO;
import lk.ijse.ccz.dao.DAOFactory;
import lk.ijse.ccz.dao.custom.InventoryDAO;
import lk.ijse.ccz.db.DbConnection;
import lk.ijse.ccz.entity.Inventory;
import lk.ijse.ccz.model.InventoryDTO;
import lk.ijse.ccz.model.OrderDetailDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryBOImpl implements InventoryBO {
    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);

    public  boolean save(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(inventoryDTO.getId(),inventoryDTO.getName(),inventoryDTO.getStock(),inventoryDTO.getPrice()));
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return inventoryDAO.delete(id);
    }

    public  boolean update(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        return inventoryDAO.update(new Inventory(inventoryDTO.getId(),inventoryDTO.getName(),inventoryDTO.getStock(),inventoryDTO.getPrice()));
    }

    public List<InventoryDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> allEntityData = inventoryDAO.getAll();
        ArrayList<InventoryDTO> allDTOData= new ArrayList<>();
        for (Inventory i : allEntityData) {
            allDTOData.add(new InventoryDTO(i.getId(),i.getName(),i.getStock(),i.getPrice()));
        }
        return allDTOData;
    }

    public  boolean updateQty(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
        return inventoryDAO.updateQty(odList);
    }

    public   boolean updateQty(OrderDetailDTO od) throws SQLException, ClassNotFoundException {
        return inventoryDAO.updateQty(od);
    }

    public  double getUnitPrice(String recipe) throws SQLException, ClassNotFoundException {
        return inventoryDAO.getUnitPrice(recipe);
    }

    public  String getProductId(String cellData) throws SQLException, ClassNotFoundException {
        return inventoryDAO.getProductId(cellData);
    }

}
