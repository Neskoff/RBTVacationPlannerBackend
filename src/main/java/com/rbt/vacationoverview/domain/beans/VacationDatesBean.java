package com.rbt.vacationoverview.domain.beans;

import com.rbt.vacationoverview.domain.models.Employee;
import com.rbt.vacationoverview.domain.models.VacationDate;
import com.rbt.vacationoverview.domain.repositories.IVacationDatesRepository;
import com.rbt.vacationoverview.domain.repositories.VacationDatesRepository;
import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class VacationDatesBean implements Serializable {
    private final IVacationDatesRepository vacationDatesRepository;
    private List<Employee> allEmployees;
    private final List<VacationDate> allVacationDates;
    private List<VacationDate> currentEmployeeVacationDates = new ArrayList<>();
    private int usedVacationCount;
    private int unusedVacationCount;
    private int totalUnusedVacation;
    private Employee currentEmployee;

    public VacationDatesBean() {
        vacationDatesRepository = new VacationDatesRepository();
        allVacationDates = vacationDatesRepository.getAllVacationDates();
        allEmployees = vacationDatesRepository.getAllEmployees();
        this.setChartData();
        totalUnusedVacation = vacationDatesRepository.unusedVacationDaysTotal();
    }

    public int vacationDatesPerMonth(int month) {
        return vacationDatesRepository.currentYearDatesCount(month);
    }

    public List<Employee> getAllEmployees() {
        return allEmployees;
    }

    public void setAllEmployees(List<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    public int getUsedVacationCount() {
        return usedVacationCount;
    }

    public void setUsedVacationCount(int usedVacationCount) {
        this.usedVacationCount = usedVacationCount;
    }

    public int getUnusedVacationCount() {
        return unusedVacationCount;
    }

    public void setUnusedVacationCount(int unusedVacationCount) {
        this.unusedVacationCount = unusedVacationCount;
    }

    public int getTotalUnusedVacation() {
        return totalUnusedVacation;
    }

    public void setTotalUnusedVacation(int totalUnusedVacation) {
        this.totalUnusedVacation = totalUnusedVacation;
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    public List<VacationDate> getCurrentEmployeeVacationDates() {
        return currentEmployeeVacationDates;
    }

    public void setCurrentEmployeeVacationDates(List<VacationDate> currentEmployeeVacationDates) {
        this.currentEmployeeVacationDates = currentEmployeeVacationDates;
    }

    public void setModalData(Employee e) {
        currentEmployee = e;
        this.setCurrentEmployeeVacationDates();
        PrimeFaces.current().ajax().update("modalForm");
    }

    public void setCurrentEmployeeVacationDates(){
        this.currentEmployeeVacationDates = vacationDatesRepository.currentEmployeeVacationDate(currentEmployee);
    }

    private void setChartData() {
        usedVacationCount = 0;
        unusedVacationCount = 0;
        boolean found;
        for (Employee employee : allEmployees) {
            found = false;
            for (VacationDate vacationDate : allVacationDates) {
                if (vacationDate.getEmployeeEmail().equals(employee.getEmail())) {
                    usedVacationCount++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                unusedVacationCount++;
            }
        }
    }
}
