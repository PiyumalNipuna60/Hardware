package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.bo.custom.impl.ProductBOImpl;
import org.example.dto.ProductDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DisplayStockFormController {
    public static ProductBOImpl productBO = new ProductBOImpl();
    public TableView tblProduct;
    public TableColumn colProductId;
    public TableColumn colProductName;
    public TableColumn colNickName;
    public Pane mainPane;
    public TableColumn colState;
    public TableColumn colCost;
    public TableColumn colQty;
    public TableColumn colAddedDate;
    public TextField txtSearch;
    public Label lblDate;
    public Label lblTime;
    public TableColumn colOption;

    public void initialize() {
        colProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        colNickName.setCellValueFactory(new PropertyValueFactory("nickName"));
        colState.setCellValueFactory(new PropertyValueFactory("state"));
        colCost.setCellValueFactory(new PropertyValueFactory("cost"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colAddedDate.setCellValueFactory(new PropertyValueFactory("addedDate"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllProduct();
        generateRealTime();

        tblProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setUpdateFields(newValue);
            }
        });
    }

    private void setUpdateFields(Object newValue) {

    }


    /*--------Load All Data-----------*/
    private void loadAllProduct() {
        tblProduct.getItems().clear();
        try {
            ArrayList<ProductDTO> all = productBO.getAll();
            for (ProductDTO dto:all) {
                tblProduct.getItems().add(
                  new ProductDTO(dto.getProductId(),dto.getProductName(),dto.getNickName(),dto.getState(),
                          dto.getCost(),dto.getQty(),dto.getAddedDate())
                );
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
