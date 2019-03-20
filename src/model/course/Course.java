/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.course;

import java.io.Serializable;

/**
 *
 * @author liamj
 */
public class Course implements Serializable {
    
    private String courseID;
    private String courseName;
    private int credit;
    
    public Course() {
        
    }
    
    public Course(String courseID, String courseName, int credit) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credit = credit;
    }
    
    public Course(String courseName, int credit) {
        this.courseName = courseName;
        this.credit = credit;       
    }
    
    public Course(String courseID) {
        this.courseID = courseID;       
    }    

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
    
    
    
    
}
