package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.bo.custom.impl.EmployeeBOImpl;
import org.example.dto.CustomerDTO;
import org.example.dto.EmployeeDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class EmployeeFormController {

    private final EmployeeBOImpl employeeBO = new EmployeeBOImpl();

    public Pane mainPane;
    public TextField txtEmployeeID;
    public TextField txtEmployeeName;
    public TextField txtAddressAddress;
    public TextField txtEmployeeContact;
    public Button btnAdd;
    public Button btnDelete;
    public Button btnUpdate;
    public TextField txtSalaryContact1;
    public TableView tblEmployee;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colOption;
    public Label lblDate;
    public Label lblPm;
    public Label lblTime;


    public void initialize() {
        generateRealTime();

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        TableColumn<EmployeeDTO, Button> lastCol = (TableColumn<EmployeeDTO, Button>) tblEmployee.getColumns().get(5);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setStyle("-fx-border-radius:red; -fx-border-radius: 10;-fx-background-radius: 10");

            btnDelete.setOnAction(event -> {
                DeleteEmployee(param.getValue().getId());
                tblEmployee.getSelectionModel().clearSelection();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });
        loadEmployeeTable();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedCustomerDetail) -> {
            if (selectedCustomerDetail != null) {
                SetUpdateData((EmployeeDTO) selectedCustomerDetail);

                String id = ((EmployeeDTO) selectedCustomerDetail).getId();
                try {
                    if (employeeBO.exist(id)){
                       btnAdd.setDisable(true);
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void SetUpdateData(EmployeeDTO dto) {
        txtEmployeeID.setText(dto.getId());
        txtEmployeeName.setText(dto.getName());
        txtAddressAddress.setText(dto.getAddress());
        txtEmployeeContact.setText(dto.getContact());
        txtSalaryContact1.setText(dto.getSalary());
    }

    private void loadEmployeeTable() {
        tblEmployee.getItems().clear();
        try {
            ArrayList<EmployeeDTO> all = employeeBO.getAll();
            for (EmployeeDTO dto : all) {
                tblEmployee.getItems().add(
                        new EmployeeDTO(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getContact(),
                                dto.getSalary()
                        ));
            }

            txtEmployeeID.setText("E00-00"+String.valueOf(all.size()+1));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void DeleteEmployee(String value) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Do You Won't Delete " + value + " ..!", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().getText().equals("Yes")) {
            try {
                if (employeeBO.delete(value)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete " + value + " ..!").show();
                    loadEmployeeTable();
                    clearTextField();
                    btnAdd.setDisable(false);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clearTextField() {
        txtEmployeeID.clear();
        txtEmployeeName.clear();
        txtAddressAddress.clear();
        txtEmployeeContact.clear();
        txtSalaryContact1.clear();
    }

    public void btnSaveEmployeeOnAction(ActionEvent actionEvent) {
        String id = txtEmployeeID.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddressAddress.getText();
        String contact = txtEmployeeContact.getText();
        String salary = txtSalaryContact1.getText();

        try {
            if (employeeBO.exist(id)) {
                new Alert(Alert.AlertType.WARNING, "Employee is exits..!").show();
            } else {
                if (employeeBO.Save(new EmployeeDTO(id, name, address, contact, salary))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Save Employee..!").show();
                    clearTextField();
                    loadEmployeeTable();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Something Wrong..!").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
        DeleteEmployee(txtEmployeeID.getText());
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
        String id = txtEmployeeID.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddressAddress.getText();
        String contact = txtEmployeeContact.getText();
        String salary = txtSalaryContact1.getText();

        try {
            if (employeeBO.update(new EmployeeDTO(id, name, address, contact, salary))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update Employee..!").show();
                clearTextField();
                loadEmployeeTable();
                btnAdd.setDisable(false);
            } else {
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
