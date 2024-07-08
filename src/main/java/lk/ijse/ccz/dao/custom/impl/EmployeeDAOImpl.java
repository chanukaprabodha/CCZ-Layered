package lk.ijse.ccz.dao.custom.impl;

import lk.ijse.ccz.dao.SQLUtill;
import lk.ijse.ccz.dao.custom.EmployeeDAO;
import lk.ijse.ccz.entity.Employee;
import lk.ijse.ccz.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    public  boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?)", entity.getEmployeeID(), entity.getName(), entity.getPosition(), entity.getAddress(), entity.getContact());
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM employee WHERE employeeId = ?", id);

    }

    public  boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE employee SET name = ?, position = ?, address = ?, contact = ? WHERE employeeId = ?", entity.getName(), entity.getPosition(), entity.getAddress(), entity.getContact(), entity.getEmployeeID());
    }

    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM employee");
        while (rst.next()){
            Employee employee = new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
            allEmployee.add(employee);
        }
        return allEmployee;
    }
}
