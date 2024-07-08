package lk.ijse.ccz.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.ccz.bo.SuperBO;
import lk.ijse.ccz.model.ConfirmOrder;
import lk.ijse.ccz.model.OrderDTO;
import lk.ijse.ccz.model.OrderDetailDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ConfirmOrderBO extends SuperBO {

    public String currentId() throws SQLException, ClassNotFoundException;

    public int getUnitPrice(String recipe) throws SQLException, ClassNotFoundException;

    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException ;

    public XYChart.Series incomeChart(XYChart.Series chart) throws SQLException, ClassNotFoundException;

    public XYChart.Series customerChart(XYChart.Series chart) throws SQLException, ClassNotFoundException;

    public double getTodayIncome(Date sqlDate) throws SQLException, ClassNotFoundException;

    public double getTotalIncome() throws SQLException, ClassNotFoundException;

    public int getTodayOrder(Date sqlDate) throws SQLException, ClassNotFoundException;

    public String getMostRecentOrderId() throws SQLException, ClassNotFoundException;

    public  boolean save(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException;

    public   boolean save(OrderDetailDTO od) throws SQLException, ClassNotFoundException ;

    public  boolean placeOrder(ConfirmOrder po) throws SQLException ;

    String findCustomer(String mobile) throws SQLException, ClassNotFoundException;

    String getCustomerName(String cusId) throws SQLException, ClassNotFoundException;

    String getCustomerEmail(String cusId) throws SQLException, ClassNotFoundException;
}
