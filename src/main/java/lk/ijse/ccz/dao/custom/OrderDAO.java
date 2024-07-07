package lk.ijse.ccz.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.ccz.dao.CrudDAO;
import lk.ijse.ccz.model.Order;

import java.sql.*;

public interface OrderDAO extends CrudDAO<Order> {

    public  String currentId() throws SQLException ;

    public  int getUnitPrice(String recipe) throws SQLException ;

    public  XYChart.Series incomeChart(XYChart.Series chart) ;

    public  XYChart.Series customerChart(XYChart.Series chart) ;

    public  double getTodayIncome(Date sqlDate) throws SQLException ;

    public  double getTotalIncome() throws SQLException ;

    public  int getTodayOrder(Date sqlDate) throws SQLException ;

    public  String getMostRecentOrderId() throws SQLException ;

}
