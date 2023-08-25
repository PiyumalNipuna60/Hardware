package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
