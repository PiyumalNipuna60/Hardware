package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.example.bo.custom.impl.ProductBOImpl;
import org.example.dto.ProductDTO;
import org.example.entity.tm.ProductTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderFormController {
    public static ProductBOImpl productBO = new ProductBOImpl();
    ArrayList<Object> AllProductDetails = new ArrayList<>();


    public TextField txtOrderQuantity;
    public TextField txtAvailableQuantity;
    public TextField txtDiscount;
    public TextField txtProductPrice;
    public TextField txtNickName;
    public TextField txtProductName;
    public Pane mainPane;
    public TextField txtSearch;

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }

    public void btnAddProductOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();
        String name = txtProductName.getText();
        String nickName = txtNickName.getText();
        int price = Integer.parseInt(txtProductPrice.getText());
        int discount = Integer.parseInt(txtDiscount.getText());
        int availableQty = Integer.parseInt(txtAvailableQuantity.getText());
        int orderQty = Integer.parseInt(txtOrderQuantity.getText());

        AllProductDetails.add(new ProductTm(id, name, nickName, price, discount, availableQty, orderQty));

        clearTextField();
        loadTableData();

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

    private void loadTableData() {

    }

    private void removeTableData() {
        for (int i = 0; i < AllProductDetails.size(); i++) {
            AllProductDetails.remove(i);
        }
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
        removeTableData();
    }

}

// load table ekai, place oprder ekai
