package lk.ijse.ccz.bo.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.ccz.bo.custom.ConfirmOrderBO;
import lk.ijse.ccz.dao.DAOFactory;
import lk.ijse.ccz.dao.custom.CustomerDAO;
import lk.ijse.ccz.dao.custom.InventoryDAO;
import lk.ijse.ccz.dao.custom.OrderDAO;
import lk.ijse.ccz.dao.custom.OrderDetailDAO;
import lk.ijse.ccz.db.DbConnection;
import lk.ijse.ccz.entity.OrderDetail;
import lk.ijse.ccz.entity.Orders;
import lk.ijse.ccz.model.ConfirmOrder;
import lk.ijse.ccz.model.OrderDTO;
import lk.ijse.ccz.model.OrderDetailDTO;

import java.sql.*;
import java.util.List;

public class ConfirmOrderBOImpl implements ConfirmOrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CONFIRM_ORDER);
    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public String currentId() throws SQLException, ClassNotFoundException {
        return orderDAO.currentId();
    }

    public int getUnitPrice(String recipe) throws SQLException, ClassNotFoundException {
        return orderDAO.getUnitPrice(recipe);
    }

    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Orders(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustomerID(),orderDTO.getTotalAmount()));
    }

    public XYChart.Series incomeChart(XYChart.Series chart) throws SQLException, ClassNotFoundException {
        return orderDAO.incomeChart(chart);
    }

    public XYChart.Series customerChart(XYChart.Series chart) throws SQLException, ClassNotFoundException {
        return orderDAO.customerChart(chart);

    }

    public double getTodayIncome(Date sqlDate) throws SQLException, ClassNotFoundException {
        return orderDAO.getTodayIncome(sqlDate);
    }

    public double getTotalIncome() throws SQLException, ClassNotFoundException {
        return orderDAO.getTotalIncome();
    }

    public int getTodayOrder(Date sqlDate) throws SQLException, ClassNotFoundException {
        return orderDAO.getTodayOrder(sqlDate);
    }

    public String getMostRecentOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getMostRecentOrderId();
    }

    public  boolean save(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.save((OrderDetail) odList);
    }

    public   boolean save(OrderDetailDTO od) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.save(new OrderDetail(od.getOrderId(), od.getIngredientId(), od.getIngredientQty(),od.getUnitPrice()));
    }

    public  boolean placeOrder(ConfirmOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.save(new Orders(po.getOrderDTO().getOrderId(), po.getOrderDTO().getOrderDate(), po.getOrderDTO().getCustomerID(), po.getOrderDTO().getTotalAmount()));

            if (isOrderSaved) {

                boolean isOrderDetailSaved = orderDetailDAO.save((OrderDetail) po.getOdList());

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

    @Override
    public String findCustomer(String mobile) throws SQLException, ClassNotFoundException {
        return customerDAO.findCustomer(mobile);
    }

    @Override
    public String getCustomerName(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerName(cusId);
    }

    @Override
    public String getCustomerEmail(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerEmail(cusId);
    }

}
