package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.bo.custom.impl.OrderBOImpl;
import org.example.bo.custom.impl.OrderDetailsBOImpl;
import org.example.bo.custom.impl.ProductBOImpl;
import org.example.dto.OrderDTO;
import org.example.dto.OrderDetailsDTO;
import org.example.dto.ProductDTO;
import org.example.entity.tm.PlaceOrderTm;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PlaceOrderFormController {
    public static ProductBOImpl productBO = new ProductBOImpl();
    public Label lblOrderCount;
    public Label lblDate;

    OrderBOImpl orderBO = new OrderBOImpl();

    private final OrderDetailsBOImpl orderDetailsBO = new OrderDetailsBOImpl();
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

        generateRealTime();
        updateOrderNumber();


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
        lblOrderCount.setText(String.valueOf(pm.getId()));
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


    /*------------------------transaction ekak gahanna one-------------------------*/
    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

        double totalPrice=0;
        double discount2=0;

        /*----calculate totalPrice----*/
        for (PlaceOrderTm product : AllProductDetails) {
            int x = product.getCost() * product.getQty();
            discount2=((double)x * product.getDiscount()/ 100);
            totalPrice=x-discount2;
            System.out.println("totalPrice "+totalPrice);
        }


//
//        String OrderId = lblOrderCount.getText();
//        for (int i = 0; i < AllProductDetails.size(); i++) {
//            String id = String.valueOf(AllProductDetails.get(i).getId());
//            String productId = AllProductDetails.get(i).getProductId();
//            String discount = String.valueOf(AllProductDetails.get(i).getDiscount());
//            int qty = AllProductDetails.get(i).getQty();
//
//            try {
//                System.out.println(OrderId);
//                if (orderDetailsBO.Save(new OrderDetailsDTO(productId,OrderId,discount,qty))){
//                    System.out.println("ok");
//                }
//            } catch (SQLException | ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        removeDataArray();
//        loadAllOrder();
//        updateOrderNumber();


    }


    private void updateOrderNumber(){
        try {
            ArrayList<OrderDTO> all1 = orderBO.getAll();
            for (OrderDTO d1:all1) {
                if (d1.getTotal()==0){
                    orderBO.delete(d1.getOrderId());
                }
            }

            ArrayList<OrderDTO> all = orderBO.getAll();
            String size = "O00" + String.valueOf(all.size() + 1);

            orderBO.Save(new OrderDTO(size,0,Date.valueOf(lblDate.getText()),0));
            lblOrderCount.setText(size);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
                int id = Integer.parseInt(lblOrderCount.getText());
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

    /*-----DATE AND TIME GENERATE------*/
    private void generateRealTime() {
        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
//            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
