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


    }


    public void searchOnKeyPressed(KeyEvent keyEvent) {

    }
    
    public void btnCancelOnAction(ActionEvent actionEvent) {

    }
    
}
