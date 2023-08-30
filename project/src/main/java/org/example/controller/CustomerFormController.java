package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.bo.custom.impl.CustomerBOImpl;
import org.example.dto.CustomerDTO;
import org.example.entity.tm.PlaceOrderTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerFormController {

    private final  CustomerBOImpl customerBO = new CustomerBOImpl();
    public Pane mainPane;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TextField txtCustomerName;
    public TextField txtCCustomerID;
    public TextField txtCustomerAddress;
    public Button btnAdd;
    public Button btnDelete;
    public Button btnUpdate;
    public Label lblTime;
    public Label lblPm;
    public Label lblDate;
    public TableColumn colOption;
    public TextField txtCustomerContact;
    public TableView tblCustomer;

    public void initialize(){
        generateRealTime();

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        TableColumn<CustomerDTO, Button> lastCol = (TableColumn<CustomerDTO, Button>) tblCustomer.getColumns().get(4);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setStyle("-fx-border-radius:red; -fx-border-radius: 10;-fx-background-radius: 10");

            btnDelete.setOnAction(event -> {
//                tblCustomer.getItems().remove(param.getValue());
                DeleteCustomer(param.getValue().getId());
                tblCustomer.getSelectionModel().clearSelection();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });
        loadCustomerTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedCustomerDetail) -> {

            if (selectedCustomerDetail != null) {
                SetUpdateData((CustomerDTO) selectedCustomerDetail);
            }
        });
    }

    private void SetUpdateData(CustomerDTO dto) {
        txtCCustomerID.setText(dto.getId());
        txtCustomerName.setText(dto.getName());
        txtCustomerAddress.setText(dto.getAddress());
        txtCustomerContact.setText(dto.getContact());
    }

    private void DeleteCustomer(String value) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Do You Won't Delete "+value+" ..!",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().getText().equals("Yes")) {
            try {
                if (customerBO.delete(value)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete "+value+" ..!").show();
                    loadCustomerTable();
                    clearTextField();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void btnSaveCustomerOnAction(ActionEvent actionEvent) {
        String id = txtCCustomerID.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String contact = txtCustomerContact.getText();

        try {
            if(customerBO.Save(new CustomerDTO(id,name,address,contact))){
                new Alert(Alert.AlertType.CONFIRMATION, "Save Customer..!").show();
                clearTextField();
                loadCustomerTable();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Something Wrong..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerTable() {
        tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> all = customerBO.getAll();
            for (CustomerDTO dto:all) {
                tblCustomer.getItems().add(
                  new CustomerDTO(
                          dto.getId(),
                          dto.getName(),
                          dto.getAddress(),
                          dto.getContact()
                  ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearTextField() {
        txtCCustomerID.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerContact.clear();
    }

    public void btnDeleteCustomerOnAction(ActionEvent actionEvent) {
        DeleteCustomer(txtCCustomerID.getText());
    }

    public void btnUpdateCustomerOnAction(ActionEvent actionEvent) {
        String id = txtCCustomerID.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String contact = txtCustomerContact.getText();

        try {
            if(customerBO.update(new CustomerDTO(id,name,address,contact))){
                new Alert(Alert.AlertType.CONFIRMATION, "Update Customer..!").show();
                clearTextField();
                loadCustomerTable();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Something Wrong..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*-----DATE AND TIME GENERATE------*/
    private void generateRealTime() {
        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("a");
            lblTime.setText(LocalDateTime.now().format(formatter));
            lblPm.setText(LocalDateTime.now().format(formatter1));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
