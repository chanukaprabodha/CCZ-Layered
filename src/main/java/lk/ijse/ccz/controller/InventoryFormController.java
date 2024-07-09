package lk.ijse.ccz.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.ccz.bo.BOFactory;
import lk.ijse.ccz.bo.custom.InventoryBO;
import lk.ijse.ccz.dto.InventoryDTO;
import lk.ijse.ccz.dto.tm.InventoryTm;
import lk.ijse.ccz.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class InventoryFormController {

    @FXML
    private TableColumn<?, ?> colIngredientId;

    @FXML
    private TableColumn<?, ?> colIngredientName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableView<InventoryTm> tblInventory;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductID;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtStock;

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INVENTORY);

    public void initialize() {
        List<InventoryDTO> inventoryDTOList = getAllInventories();
        setCellValueFactory();
        loadInventoryTable();
    }

    private void setCellValueFactory() {
        colIngredientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIngredientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    private void loadInventoryTable() {
        ObservableList<InventoryTm> tmList = FXCollections.observableArrayList();

        try {
            List<InventoryDTO> inventoryDTOList = inventoryBO.getAll();
            for (InventoryDTO inventoryDTO : inventoryDTOList) {
                InventoryTm inventoryTm = new InventoryTm(
                        inventoryDTO.getId(),
                        inventoryDTO.getName(),
                        inventoryDTO.getStock(),
                        inventoryDTO.getPrice()
                );

                if(inventoryDTO.getStock()<= 2){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Low Stock Warning");
                    alert.setContentText("Stock of " + inventoryDTO.getName() + " is running low! (" + inventoryDTO.getStock() + " left)");
                    alert.showAndWait();
                }

                tmList.add(inventoryTm);
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        tblInventory.setItems(tmList);
    }

    private List<InventoryDTO> getAllInventories() {
        List<InventoryDTO> inventoryDTOList = null;
        try {
            inventoryDTOList = inventoryBO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return inventoryDTOList;
    }

    private void clearFields() {
        txtProductID.setText("");
        txtProductName.setText("");
        txtStock.setText("");
        txtPrice.setText("");
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtProductID.getText();
        String name = txtProductName.getText();
        double stock = Double.parseDouble(txtStock.getText());
        double price = Double.parseDouble(txtPrice.getText());

        if (isValid()){
            try {
                boolean isSaved = inventoryBO.save(new InventoryDTO(id, name, stock, price));
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "Inventory saved!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
        loadInventoryTable();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtProductID.getText();

        try {
            boolean isDeleted = inventoryBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Inventory deleted!").show();
                loadInventoryTable();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblInventory.refresh();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtProductID.getText();
        String name = txtProductName.getText();
        double stock = Double.parseDouble(txtStock.getText());
        double price = Double.parseDouble(txtPrice.getText());

        if (isValid()){
            try {
                boolean isSaved = inventoryBO.update(new InventoryDTO(id, name, stock, price));
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "Inventory saved!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
        loadInventoryTable();
    }

    @FXML
    void invenClickOnAction(MouseEvent event) {
        InventoryTm selectedItem = tblInventory.getSelectionModel().getSelectedItem();

        txtProductID.setText(selectedItem.getId());
        txtProductName.setText(selectedItem.getName());
        txtStock.setText(String.valueOf(selectedItem.getStock()));
        txtPrice.setText(String.valueOf(selectedItem.getPrice()));
    }

    @FXML
    void IngredientIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.Iid,txtProductID);
    }

    @FXML
    void IngredientNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.NAME,txtProductName);
    }

    @FXML
    void priceOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.STOCK,txtStock);
    }

    @FXML
    void stockOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.ccz.util.TextField.PRICE,txtPrice);
    }

    public boolean isValid() {
        if (!Regex.setTextColor(lk.ijse.ccz.util.TextField.Iid,txtProductID)) {
            return false;
        }
        if (!Regex.setTextColor(lk.ijse.ccz.util.TextField.STOCK,txtStock)) {
            return false;
        }
        if (!Regex.setTextColor(lk.ijse.ccz.util.TextField.PRICE,txtPrice)) {
            return false;
        }
        return true;
    }

}
