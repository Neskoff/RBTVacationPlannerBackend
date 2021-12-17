package com.rbt.vacationoverview.domain.repositories;

import com.rbt.vacationoverview.domain.models.Employee;
import com.rbt.vacationoverview.domain.models.VacationDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VacationDatesRepository implements IVacationDatesRepository {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistent");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public List<VacationDate> getAllVacationDates() {
        TypedQuery<VacationDate> query = entityManager.createNamedQuery(VacationDate.getAllDates, VacationDate.class);
        return query.getResultList();
    }

    @Override
    public int currentYearDatesCount(int month) {
        int count = 0;
        List<VacationDate> allDates = this.getAllVacationDates();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        for (VacationDate vacationDate : allDates) {
            c1.setTime(vacationDate.getVacationStartDate());
            c2.setTime(vacationDate.getVacationEndDate());
            if (c1.get(Calendar.MONTH) == month && (c1.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
                    || c2.get(Calendar.MONTH) == month && (c2.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> query = entityManager.createNamedQuery(Employee.getAllEmployees, Employee.class);
        return query.getResultList();
    }

    @Override
    public int unusedVacationDaysTotal() {
        int count = 0;
        List<VacationDate> allDates = this.getAllVacationDates();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        for (Employee employee : this.getAllEmployees()) {
            int unusedDates = 0;
            for (VacationDate vacationDate : allDates) {
                c1.setTime(vacationDate.getVacationStartDate());
                c2.setTime(vacationDate.getVacationEndDate());
                if (vacationDate.getEmployeeEmail().equals(employee.getEmail())) {
                    unusedDates += ChronoUnit.DAYS.between(c1.toInstant(), c2.toInstant());
                }
            }
            unusedDates = employee.getVacationDays() - unusedDates;
            unusedDates = -unusedDates;
            count += unusedDates;
            unusedDates = 0;
        }
        return count;
    }

    @Override
    public List<VacationDate> currentEmployeeVacationDate(Employee employee) {
        List<VacationDate> vacationDates = this.getAllVacationDates();
        List<VacationDate> currentEmployeeVacationDates = new ArrayList<>();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
            for (VacationDate vacationDate : vacationDates) {
                c1.setTime(vacationDate.getVacationStartDate());
                c2.setTime(vacationDate.getVacationEndDate());
                if (vacationDate.getEmployeeEmail().equals(employee.getEmail())) {
                    currentEmployeeVacationDates.add(vacationDate);
                }
            }
        return currentEmployeeVacationDates;
    }
}
