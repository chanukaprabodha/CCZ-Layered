package lk.ijse.ccz.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.dao.SuperDAO;
import lk.ijse.ccz.entity.Orders;

import java.sql.*;

public interface OrderDAO extends CrudDAO<Orders> {

    public  String currentId() throws SQLException, ClassNotFoundException;

    public  int getUnitPrice(String recipe) throws SQLException, ClassNotFoundException;

    public  XYChart.Series incomeChart(XYChart.Series chart) throws SQLException, ClassNotFoundException;

    public  XYChart.Series customerChart(XYChart.Series chart) throws SQLException, ClassNotFoundException;

    public  double getTodayIncome(Date sqlDate) throws SQLException, ClassNotFoundException;

    public  double getTotalIncome() throws SQLException, ClassNotFoundException;

    public  int getTodayOrder(Date sqlDate) throws SQLException, ClassNotFoundException;

    public  String getMostRecentOrderId() throws SQLException, ClassNotFoundException;

}
