package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import config.DBConnection;
import models.Enrollment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public boolean addEnrollment(Enrollment enrollment) {
        try {
            Connection con = DBConnection.getConnection();
            
            String query =
                    "INSERT INTO enrollments(student_id, course_id, enrollment_date) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setString(3, enrollment.getEnrollmentDate());

            ps.executeUpdate();

            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateEnrollment(Enrollment enrollment) {

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "UPDATE enrollments SET student_id=?, course_id=?, enrollment_date=? WHERE enrollment_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setString(3, enrollment.getEnrollmentDate());
            ps.setInt(4, enrollment.getEnrollmentId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEnrollment(int id) {

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "DELETE FROM enrollments WHERE enrollment_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Enrollment> getAllEnrollments() {

        List<Enrollment> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM enrollments";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("enrollment_date")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}