package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class MainLoginFormController{
    public Pane pbAdminColor;
    public Button btnAdmin;
    public Pane pbUserColor;
    public Button btnUser;
    public Pane paneMain;
    public Pane pbUser2;

    public void initialize() {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/view/AdminLoginForm.fxml"));
            paneMain.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUserOnAction() {
        btnAdmin.setStyle("-fx-background-color: red;-fx-border-radius:30;-fx-background-radius:30");
        btnUser.setStyle("-fx-background-color: white;-fx-border-radius:30;-fx-background-radius:30;");
        pbUserColor.setStyle("-fx-background-color: white");
        pbAdminColor.setStyle("-fx-background-color: red");

        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/view/userLoginForm.fxml"));
            paneMain.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAdminOnAction() {
        btnAdmin.setStyle("-fx-background-color: white;-fx-border-radius:30;-fx-background-radius:30");
        btnUser.setStyle("-fx-background-color: red;-fx-border-radius:30;-fx-background-radius:30");
        pbAdminColor.setStyle("-fx-background-color: white");
        pbUserColor.setStyle("-fx-background-color: red");

        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/view/AdminLoginForm.fxml"));
            paneMain.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
