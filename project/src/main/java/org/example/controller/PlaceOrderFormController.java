package org.example.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.example.bo.custom.impl.ProductBOImpl;
import org.example.dto.ProductDTO;
import org.example.entity.tm.PlaceOrderTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderFormController {
    public static ProductBOImpl productBO = new ProductBOImpl();
    public TableView tblOrder;
    public TableColumn colProductId;
    public TableColumn colPName;
    public TableColumn colNickName;
    public TableColumn colPrice;
    public TableColumn colDiscount;
    public TableColumn colQty;
    public TableColumn colOption;
    public TableColumn colID;
    public Button btnAdd;
    public Label lblCount;
    ArrayList<PlaceOrderTm> AllProductDetails = new ArrayList<>();


    public TextField txtOrderQuantity;
    public TextField txtAvailableQuantity;
    public TextField txtDiscount;
    public TextField txtProductPrice;
    public TextField txtNickName;
    public TextField txtProductName;
    public Pane mainPane;
    public TextField txtSearch;

    public void initialize() throws SQLException, ClassNotFoundException {

        lblCount.setText("1");

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colNickName.setCellValueFactory(new PropertyValueFactory<>("nickName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        TableColumn<PlaceOrderTm, Button> lastCol = (TableColumn<PlaceOrderTm, Button>) tblOrder.getColumns().get(7);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(event -> {
                tblOrder.getItems().remove(param.getValue());
                tblOrder.getSelectionModel().clearSelection();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });
        loadAllOrder();

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {

            if (selectedOrderDetail != null) {
                btnAdd.setText("Update PRODUCT");
                SetUpdateData((PlaceOrderTm) selectedOrderDetail);
            } else {
                btnAdd.setText("ADD PRODUCT");
                clearTextField();
            }
        });
    }

    private void SetUpdateData(PlaceOrderTm pm) {

        ProductDTO search;
        try {
            search = productBO.search(pm.getProductId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lblCount.setText(String.valueOf(pm.getId()));
        txtSearch.setText(pm.getProductId());
        txtProductName.setText(pm.getProductName());
        txtNickName.setText(pm.getNickName());
        txtProductPrice.setText(String.valueOf(pm.getCost()));
        txtDiscount.setText(String.valueOf(pm.getDiscount()));
        txtAvailableQuantity.setText(String.valueOf(search.getQty()));
        txtOrderQuantity.setText(String.valueOf(pm.getQty()));
    }


    private void loadAllOrder() {
        tblOrder.getItems().clear();
        for (PlaceOrderTm dto : AllProductDetails) {
            tblOrder.getItems().add(
                    new PlaceOrderTm(
                            dto.getId(),
                            dto.getProductId(),
                            dto.getProductName(),
                            dto.getNickName(),
                            dto.getCost(),
                            dto.getDiscount(),
                            dto.getQty()));

        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }

    public void btnAddProductOnAction(ActionEvent actionEvent) {
        if (txtDiscount.getText().equals("") || txtSearch.getText().equals("") || txtOrderQuantity.getText().equals("") || txtProductPrice.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Add Complete Data").show();
        } else {
            if (btnAdd.getText().equals("ADD PRODUCT")) {
                String id = txtSearch.getText();
                String name = txtProductName.getText();
                String nickName = txtNickName.getText();
                int price = Integer.parseInt(txtProductPrice.getText());
                int discount = Integer.parseInt(txtDiscount.getText());
                int availableQty = Integer.parseInt(txtAvailableQuantity.getText());
                int orderQty = Integer.parseInt(txtOrderQuantity.getText());

                AllProductDetails.add(new PlaceOrderTm(AllProductDetails.size() + 1, id, name, nickName, price, discount, orderQty));

                clearTextField();
                loadAllOrder();
            } else {
                int id = Integer.parseInt(lblCount.getText());
                AllProductDetails.set(id - 1, new PlaceOrderTm(id, txtSearch.getText(), txtProductName.getText(), txtNickName.getText(),
                        Integer.parseInt(txtProductPrice.getText()), Integer.parseInt(txtDiscount.getText()), Integer.parseInt(txtOrderQuantity.getText())));
                loadAllOrder();
                clearTextField();
            }
        }
    }

    private void clearTextField() {
        txtSearch.clear();
        txtProductName.clear();
        txtNickName.clear();
        txtDiscount.clear();
        txtOrderQuantity.clear();
        txtAvailableQuantity.clear();
        txtProductPrice.clear();
    }


    public void searchOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                ProductDTO search = productBO.search(txtSearch.getText());
                if (search != null) {
                    txtProductName.setText(search.getProductName());
                    txtNickName.setText(search.getNickName());
                    txtProductPrice.setText(String.valueOf(search.getCost()));
                    txtAvailableQuantity.setText(String.valueOf(search.getQty()));
                } else {
                    new Alert(Alert.AlertType.ERROR, "Empty Result..!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clearTextField();
        removeDataArray();
        loadAllOrder();
    }

    private void removeDataArray() {
        for (int i = 0; i < AllProductDetails.size(); i++) {
            AllProductDetails.remove(i);
        }
    }

}
