/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.course.Course;
import model.student.Student;
import model.student.StudentDAO;
import model.teacher.Teacher;
import util.DBConnection;
import util.DBUtil;

/**
 *
 * @author liamj
 */
public class EnrollmentDAO {
    
    //CREATE

    public static int insert(Student student, Course course) throws SQLException, ClassNotFoundException {

        PreparedStatement ps = null;

        String query
                = "INSERT INTO STUDENT_COURSE_FP (studentID, courseID) "
                + "VALUES (?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, student.getStudentID());
            ps.setString(2, course.getCourseID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);

        }

    }

    public static int insert(Teacher teacher, Course course) throws SQLException, ClassNotFoundException {

        PreparedStatement ps = null;

        String query
                = "INSERT INTO TEACHER_COURSE_FP (teacherID, courseID) "
                + "VALUES (?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, teacher.getTeacherID());
            ps.setString(2, course.getCourseID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);

        }

    }

    public static int addResult(Student student, Course course, Enrollment enrollment) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO STUDENT_COURSE_RESULT_FP (studentID, courseID, mark, sessionnumber) "
                + "VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, student.getStudentID());
            ps.setString(2, course.getCourseID());
            ps.setInt(3, enrollment.getMark());
            ps.setInt(4, enrollment.getSessionNumber());

            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    //read
    public static boolean studentInCourse(String studentID, String courseID) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM student_course_fp "
                + "WHERE studentID = ? and courseID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, studentID);
            ps.setString(2, courseID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }
    
    
    public List<Enrollment> getAllResults() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        ArrayList<Enrollment> resultList = new ArrayList();
        String query = "select r.resultid, r.studentid, s.firstname, s.lastname, r.courseid, c.coursename, r.mark, r.sessionnumber " +
                       "from student_course_result_fp r JOIN STUDENT_FP s on (r.STUDENTID = s.STUDENTID) " +
                       "join COURSE_FP c on (r.COURSEID = c.COURSEID)";

        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setResultID(result.getString(1));
                enrollment.setStudentID(result.getString(2));
                enrollment.setFirstName(result.getString(3));
                enrollment.setLastName(result.getString(4));
                enrollment.setCourseID(result.getString(5));
                enrollment.setCourseName(result.getString(6));                
                enrollment.setMark(result.getInt(7));
                enrollment.setSessionNumber(result.getInt(8));
                resultList.add(enrollment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultList;
    }
    
    //UPDATE
    public static int updateResult(Student student, Course course, Enrollment enrollment) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE student_course_result_fp SET MARK=?, SESSIONNUMBER=? "
                + "WHERE STUDENTID = ? AND COURSEID=?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, enrollment.getMark());
            ps.setInt(2, enrollment.getSessionNumber());
            ps.setString (3, student.getStudentID());
            ps.setString (4, course.getCourseID());
            
        return    ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }    
    

}
