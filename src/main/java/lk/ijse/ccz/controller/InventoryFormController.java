package lk.ijse.ccz.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.ccz.dao.custom.InventoryDAO;
import lk.ijse.ccz.model.Inventory;
import lk.ijse.ccz.model.tm.InventoryTm;
import lk.ijse.ccz.dao.custom.impl.InventoryDAOImpl;
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

    InventoryDAO inventoryDAO = new InventoryDAOImpl();


    public void initialize() {
        List<Inventory> inventoryList = getAllInventories();
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
            List<Inventory> inventoryList = inventoryDAO.getAll();
            for (Inventory inventory : inventoryList) {
                InventoryTm inventoryTm = new InventoryTm(
                        inventory.getId(),
                        inventory.getName(),
                        inventory.getStock(),
                        inventory.getPrice()
                );

                if(inventory.getStock()<= 2){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Low Stock Warning");
                    alert.setContentText("Stock of " + inventory.getName() + " is running low! (" + inventory.getStock() + " left)");
                    alert.showAndWait();
                }

                tmList.add(inventoryTm);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tblInventory.setItems(tmList);
    }

    private List<Inventory> getAllInventories() {
        List<Inventory> inventoryList = null;
        try {
            inventoryList = inventoryDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inventoryList;
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
                boolean isSaved = inventoryDAO.save(new Inventory(id, name, stock, price));
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "Inventory saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
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
            boolean isDeleted = inventoryDAO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Inventory deleted!").show();
                loadInventoryTable();
                clearFields();
            }
        } catch (SQLException e) {
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
                boolean isSaved = inventoryDAO.update(new Inventory(id, name, stock, price));
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "Inventory saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
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
