package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashBoardFormController {
    public Pane mainPane;

    public void initialize(){
        setUi("PlaceOrderForm");
    }

    public void btnDisplayStockOnAction(ActionEvent actionEvent) {
        setUi("PlaceOrderForm");
    }

    public void btnPlaceInvoiceOnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void setUi(String url){
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/view/"+url+".fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
