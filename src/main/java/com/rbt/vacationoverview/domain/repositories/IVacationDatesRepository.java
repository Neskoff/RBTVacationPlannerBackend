package com.rbt.vacationoverview.domain.repositories;

import com.rbt.vacationoverview.domain.models.Employee;
import com.rbt.vacationoverview.domain.models.VacationDate;

import java.util.List;

public interface IVacationDatesRepository {
    List<VacationDate> getAllVacationDates();
    int currentYearDatesCount(int month);
    List<Employee> getAllEmployees();
    int unusedVacationDaysTotal();
    List<VacationDate>currentEmployeeVacationDate(Employee employee);
}
