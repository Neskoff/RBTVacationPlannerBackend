package com.rbt.vacationoverview.domain.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VACATION_DATES")
@IdClass(VacationDateCompositeKey.class)
@NamedQuery(name = VacationDate.getAllDates, query = "SELECT v FROM VacationDate v" )
public class VacationDate {
    public static final String getAllDates = "getAllDates";
    @Id
    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;
    @Id
    @Column(name = "VACATION_START_DATE")
    private Date vacationStartDate;
    @Column(name = "VACATION_END_DATE", nullable = false)
    private Date vacationEndDate;

    public VacationDate() {
    }

    public VacationDate(String employeeEmail, Date vacationStartDate, Date vacationEndDate) {
        this.employeeEmail = employeeEmail;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Date getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(Date vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public Date getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(Date vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }
}
