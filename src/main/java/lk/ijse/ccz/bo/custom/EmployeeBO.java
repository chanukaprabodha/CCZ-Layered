package lk.ijse.ccz.bo.custom;

import lk.ijse.ccz.bo.SuperBO;
import lk.ijse.ccz.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;
}
