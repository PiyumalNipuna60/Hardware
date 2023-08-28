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
import org.example.entity.tm.ProductTm;

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
        if (txtSearch.equals("") || txtDiscount.equals("") || txtOrderQuantity.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Add Complete Data").show();
        } else {
            String id = txtSearch.getText();
            String name = txtProductName.getText();
            String nickName = txtNickName.getText();
            int price = Integer.parseInt(txtProductPrice.getText());
            int discount = Integer.parseInt(txtDiscount.getText());
            int availableQty = Integer.parseInt(txtAvailableQuantity.getText());
            int orderQty = Integer.parseInt(txtOrderQuantity.getText());

            AllProductDetails.add(new PlaceOrderTm(AllProductDetails.size(), id, name, nickName, price, discount, orderQty));

            clearTextField();
            loadAllOrder();
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
    }

    private void removeDataArray() {
        for (int i = 0; i < AllProductDetails.size(); i++) {
            AllProductDetails.remove(i);
        }
    }

}
