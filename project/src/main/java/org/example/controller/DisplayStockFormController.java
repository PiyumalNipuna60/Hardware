package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.bo.custom.impl.ProductBOImpl;
import org.example.dto.ProductDTO;
import org.example.entity.tm.ProductTMI;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class DisplayStockFormController {

    private final ProductBOImpl productBO = new ProductBOImpl();

    public Pane mainPane;
    public TableView tblProduct;
    public TableColumn colProductId;
    public TableColumn colProductName;
    public TableColumn colNickName;
    public TableColumn colState;
    public TableColumn colCost;
    public TableColumn colQty;
    public TableColumn colAddedDate;
    public TextField txtSearch;
    public Label lblTime;
    public Label lblDate;
    public TableColumn colOption;

    public void initialize() {
        colProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        colNickName.setCellValueFactory(new PropertyValueFactory("nickName"));
        colState.setCellValueFactory(new PropertyValueFactory("state"));
        colCost.setCellValueFactory(new PropertyValueFactory("cost"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colAddedDate.setCellValueFactory(new PropertyValueFactory("addedDate"));
        TableColumn<ProductTMI, Button> lastCol = (TableColumn<ProductTMI, Button>) tblProduct.getColumns().get(7);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setStyle("-fx-border-color: red; -fx-border-radius: 20px; -fx-background-radius: 20px");

            btnDelete.setOnAction(event -> {
//                tblProduct.getItems().remove(param.getValue());
                deleteProduct(param.getValue());
                tblProduct.getSelectionModel().clearSelection();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        LoadAllData();
    }

    private void deleteProduct(ProductTMI value) {
        Alert alert = new Alert(AlertType.WARNING, "Do You Won't Delete"+value.getProductId()+" ..!",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().getText().equals("Yes")) {
            try {
                if (productBO.delete(value.getProductId())) {
                    new Alert(AlertType.CONFIRMATION, "Delete"+value.getProductId()+" ..!").show();
                    LoadAllData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void formatSystem() {
        System.out.println("delete");
    }

    private void LoadAllData() {
        tblProduct.getItems().clear();
        try {
            ArrayList<ProductDTO> allProduct = productBO.getAll();
            for (ProductDTO tm:allProduct) {
                tblProduct.getItems().add(
                        new ProductTMI(tm.getProductId(),tm.getProductName(),tm.getNickName(),tm.getState(),
                                tm.getCost(),tm.getQty(),tm.getAddedDate()
                        ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOnKeyPressed(KeyEvent keyEvent) {

    }


    /*-----DATE AND TIME GENERATE------*/
    private void generateRealTime() {
        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
