package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EmployeeFormController {
    public Pane mainPane;
    public TextField txtEmployeeID;
    public TextField txtEmployeeName;
    public TextField txtAddressAddress;
    public TextField txtEmployeeContact;
    public Button btnAdd;
    public Button btnDelete;
    public Button btnUpdate;
    public TextField txtSalaryContact1;
    public TableView tblCustomer;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colOption;

    public void btnSaveEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
    }
}
