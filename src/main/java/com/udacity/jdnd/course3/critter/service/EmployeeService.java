package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EmployeeService {
    @Autowired
    EmployeesRepository employeesRepository;

    public Employee saveEmployee(Employee employee) {
        return employeesRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeesRepository.findAll();
    }

    public Employee findById(Long employeeId) {
        return employeesRepository.getOne(employeeId);
    }
}
