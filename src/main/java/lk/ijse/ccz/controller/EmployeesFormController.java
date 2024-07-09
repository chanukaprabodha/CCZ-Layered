package lk.ijse.ccz.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.ccz.bo.BOFactory;
import lk.ijse.ccz.bo.custom.EmployeeBO;
import lk.ijse.ccz.dto.EmployeeDTO;
import lk.ijse.ccz.dto.tm.EmployeeTm;
import javafx.scene.layout.AnchorPane;
import lk.ijse.ccz.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class EmployeesFormController {
    @FXML
    private AnchorPane employeePane;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmployeeID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPosition;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize() {
        List<EmployeeDTO> employeeDTOList = getAllEmployee();
        setCellValueFactory();
        loadEmployeeTable();

    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        try{
            List<EmployeeDTO> employeeDTOList = employeeBO.getAll();
            for (EmployeeDTO employeeDTO : employeeDTOList) {
                EmployeeTm employeeTm = new EmployeeTm(
                        employeeDTO.getEmployeeID(),
                        employeeDTO.getName(),
                        employeeDTO.getPosition(),
                        employeeDTO.getAddress(),
                        employeeDTO.getContact()
                );
                tmList.add(employeeTm);
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        tblEmployee.setItems(tmList);
    }

    private void setCellValueFactory() {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private List<EmployeeDTO> getAllEmployee() {
        List<EmployeeDTO> employeeDTOList = null;
        try {
            employeeDTOList = employeeBO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeeDTOList;
    }

    @FXML
    void btnEmpAddOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtName.getText();
        String email = txtPosition.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        if(isValid()){
            try {
                boolean isSaved = employeeBO.save(new EmployeeDTO(id, name, email,address, contact));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
        loadEmployeeTable();
    }

    @FXML
    void btnEmpClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnEmpDeleteOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();

        try {
            boolean isDeleted = employeeBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
                loadEmployeeTable();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadEmployeeTable();
    }

    @FXML
    void btnEmpUpdateOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtName.getText();
        String email = txtPosition.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        if (isValid()) {
            try {
                boolean isSaved = employeeBO.update(new EmployeeDTO(id, name, email,address, contact));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
        loadEmployeeTable();
    }

    private void clearFields() {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtPosition.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }

    @FXML
    void empTableOnClick(MouseEvent event) {

        EmployeeTm tm = tblEmployee.getSelectionModel().getSelectedItem();
        txtEmployeeId.setText(tm.getEmployeeId());
        txtName.setText(tm.getName());
        txtPosition.setText(tm.getPosition());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());

    }

    @FXML
    void addressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.ADDRESS,txtAddress);
    }

    @FXML
    void contactOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.CONTACT,txtContact);
    }

    @FXML
    void employeeIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.Eid, txtEmployeeId);
    }

    @FXML
    void nameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.NAME,txtName);
    }

    @FXML
    void positionOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.NAME,txtPosition);
    }

    public boolean isValid() {
        if (!Regex.setTextColor(lk.ijse.ccz.util.TextField.Eid,txtEmployeeId)) {
            return false;
        }
        if (!Regex.setTextColor(lk.ijse.ccz.util.TextField.NAME,txtName)) {
            return false;
        }
        if (!Regex.setTextColor(lk.ijse.ccz.util.TextField.NAME,txtPosition)) {
            return false;
        }
        if (!Regex.setTextColor(lk.ijse.ccz.util.TextField.CONTACT,txtContact)) {
            return false;
        }
        if (!Regex.setTextColor(lk.ijse.ccz.util.TextField.ADDRESS,txtAddress)) {
            return false;
        }
        return true;
    }

}
