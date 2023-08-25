package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
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
import java.util.stream.Collectors;

public class PlaceOrderFormController {
    public static ProductBOImpl productBO = new ProductBOImpl();
    public TableView tblOrder;
    public TableColumn colProductId;
    public TableColumn colPName;
    public TableColumn colNickName;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colOption;
    public TableColumn ID;
    ArrayList<PlaceOrderTm> AllProductDetails = new ArrayList<>();


    public TextField txtOrderQuantity;
    public TextField txtAvailableQuantity;
    public TextField txtDiscount;
    public TextField txtProductPrice;
    public TextField txtNickName;
    public TextField txtProductName;
    public Pane mainPane;
    public TextField txtSearch;

    public void initialize() {
        colProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        colPName.setCellValueFactory(new PropertyValueFactory("productName"));
        colNickName.setCellValueFactory(new PropertyValueFactory("nickName"));
        colPrice.setCellValueFactory(new PropertyValueFactory("cost"));
        colDiscount.setCellValueFactory(new PropertyValueFactory("qty"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        TableColumn<PlaceOrderTm, Button> lastCol = (TableColumn<PlaceOrderTm, Button>) tblOrder.getColumns().get(7);


        /*-----delete Button Action-----*/
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setStyle("-fx-border-color: red; -fx-border-radius: 10px");

            btnDelete.setOnAction(event -> {
                AllProductDetails.remove(param.getValue());
                tblOrder.getSelectionModel().clearSelection();
                loadTableData();
                System.out.println(AllProductDetails);
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setUpdateFields((PlaceOrderTm) newValue);
            }
        });
    }

    private void setUpdateFields(PlaceOrderTm newValue) {
        txtSearch.setText(newValue.getProductId());
        txtProductName.setText(newValue.getProductName());
        txtNickName.setText(newValue.getNickName());
        txtDiscount.setText(String.valueOf(newValue.getDiscount()));
        txtOrderQuantity.setText(String.valueOf(newValue.getQty()));
        txtProductPrice.setText(String.valueOf(newValue.getCost()));
    }


    public void btnAddProductOnAction(ActionEvent actionEvent) {
        if (txtSearch.getText().equals("") | txtOrderQuantity.equals("")) {
            new Alert(Alert.AlertType.CONFIRMATION, "Complete date add..!").show();
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
            loadTableData();
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

    private void loadTableData() {
        tblOrder.getItems().clear();
        for (PlaceOrderTm e1 : AllProductDetails) {
            tblOrder.getItems().add(
                    new PlaceOrderTm(e1.getId(), e1.getProductId(), e1.getProductName(), e1.getNickName(), e1.getCost(),
                            e1.getDiscount(), e1.getQty()));
        }
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

        if (new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ButtonType.YES, ButtonType.NO).showAndWait().get().equals(ButtonType.YES)) {
            try {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Cansel Successfully!").show();
                removeTableData();
                System.out.println(AllProductDetails);
                loadTableData();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            }
        }
    }


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }

}


// load table ekai, place oprder ekai
