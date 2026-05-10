package controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.EnrollmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Enrollment;
import javafx.scene.control.Alert;

public class MainController {

    @FXML
    private ComboBox<Integer> studentIdCombo;

    @FXML
    private ComboBox<Integer> courseIdCombo;

    @FXML
    private TextField dateField;

    @FXML
    private TableView<Enrollment> enrollmentTable;

    @FXML
    private TableColumn<Enrollment, Integer> colEnrollmentId;

    @FXML
    private TableColumn<Enrollment, Integer> colStudentId;

    @FXML
    private TableColumn<Enrollment, Integer> colCourseId;

    @FXML
    private TableColumn<Enrollment, String> colDate;

    private EnrollmentDAO dao = new EnrollmentDAO();

    private ObservableList<Enrollment> list =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colEnrollmentId.setCellValueFactory(new PropertyValueFactory<>("enrollmentId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("enrollmentDate"));

        studentIdCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        courseIdCombo.setItems(FXCollections.observableArrayList(101, 102, 103, 104));

        loadData();

        enrollmentTable.setOnMouseClicked(e -> {

            Enrollment en = enrollmentTable.getSelectionModel().getSelectedItem();

            if (en != null) {
                studentIdCombo.setValue(en.getStudentId());
                courseIdCombo.setValue(en.getCourseId());
                dateField.setText(en.getEnrollmentDate());
            }
        });
    }

    @FXML
    public void addEnrollment() {

        try {

            Integer studentId = studentIdCombo.getValue();
            Integer courseId = courseIdCombo.getValue();
            String date = dateField.getText();

            if (studentId == null || courseId == null || date.isEmpty()) {
                showAlert("Please fill all fields");
                return;
            }

            Enrollment e = new Enrollment(0, studentId, courseId, date);

            dao.addEnrollment(e);

            loadData();
            clear();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void updateEnrollment() {

        Enrollment selected = enrollmentTable.getSelectionModel().getSelectedItem();

        if (selected == null) return;

        selected.setStudentId(studentIdCombo.getValue());
        selected.setCourseId(courseIdCombo.getValue());
        selected.setEnrollmentDate(dateField.getText());

        dao.updateEnrollment(selected);

        loadData();
        clear();
    }

    @FXML
    public void deleteEnrollment() {

        Enrollment selected = enrollmentTable.getSelectionModel().getSelectedItem();

        if (selected != null) {

            dao.deleteEnrollment(selected.getEnrollmentId());

            loadData();
            clear();
        }
    }

    @FXML
    public void clearData() {
        clear();
    }

    private void loadData() {
        list.clear();
        list.addAll(dao.getAllEnrollments());
        enrollmentTable.setItems(list);
    }

    private void clear() {
        studentIdCombo.setValue(null);
        courseIdCombo.setValue(null);
        dateField.clear();
    }

    private void showAlert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.showAndWait();
    }
}