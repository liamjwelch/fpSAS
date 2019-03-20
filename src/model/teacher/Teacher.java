/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.teacher;

import java.io.Serializable;

/**
 *
 * @author liamj
 */
public class Teacher implements Serializable {
    
    private String teacherID;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private int salary;
    
    //full constructor
    public Teacher (String teacherID, String firstName, String lastName, String address, String city, String country, String phoneNumber, int salary){
        this.teacherID = teacherID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.salary = salary;        
    }
    
    //to print all students 
    public Teacher (String firstName, String lastName, String address, String city, String country, String phoneNumber, int salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.salary = salary;        
    }
    
    //to test
    public Teacher (String firstName, String lastName, String address, String city, String country, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }    
    
    
    //find student based on ID
    public Teacher(String teacherID) {
        this.teacherID = teacherID;
    }
    
    //constructor with no arguments
    public Teacher() {
        
    }    
    
    //getters and setters

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    
    
    
}
