package com.rbt.vacationoverview.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEES")
@NamedQuery(name = Employee.getAllEmployees, query = "SELECT e FROM Employee e")
public class Employee {
    public static final String getAllEmployees = "getAllEmployees";
    @Id
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Column(name = "VACATION_DAYS", nullable = false)
    private int vacationDays;

    public Employee() {
    }

    public Employee(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

