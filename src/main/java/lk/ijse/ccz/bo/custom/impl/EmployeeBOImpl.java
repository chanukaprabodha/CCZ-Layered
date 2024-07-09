package lk.ijse.ccz.bo.custom.impl;

import lk.ijse.ccz.bo.custom.EmployeeBO;
import lk.ijse.ccz.dao.DAOFactory;
import lk.ijse.ccz.dao.custom.EmployeeDAO;
import lk.ijse.ccz.entity.Employee;
import lk.ijse.ccz.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployeeID(),employeeDTO.getName(),employeeDTO.getPosition(),employeeDTO.getAddress(),employeeDTO.getContact()));
    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getEmployeeID(),employeeDTO.getName(),employeeDTO.getPosition(),employeeDTO.getAddress(),employeeDTO.getContact()));
    }

    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployeeData = employeeDAO.getAll();
        ArrayList<EmployeeDTO> allDTOData= new ArrayList<>();
        for (Employee e : allEmployeeData) {
            allDTOData.add(new EmployeeDTO(e.getEmployeeID(),e.getName(),e.getPosition(),e.getAddress(),e.getContact()));
        }
        return allDTOData;
    }
}
