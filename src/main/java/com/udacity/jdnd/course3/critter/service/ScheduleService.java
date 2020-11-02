package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

//    @Autowired
//    public ScheduleService(ScheduleRepository scheduleRepository, CustomerRepository customerRepository, EmployeesRepository employeesRepository, PetRepository petRepository) {
//        this.scheduleRepository = scheduleRepository;
//        this.customerRepository = customerRepository;
//        this.employeesRepository = employeesRepository;
//        this.petRepository = petRepository;
//    }

    public List<Schedule> getAllSchedules() {
        return this.scheduleRepository.findAll();
    }

    public List<Schedule> getAllSchedulesForPet(Long petId) {
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.getAllByPetsContains(pet);
    }

    public List<Schedule> getAllSchedulesForEmployee(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.getAllByEmployeesContains(employee);
    }
    public List<Schedule> getAllSchedulesForCustomer(Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        return scheduleRepository.getAllByPetsIn(customer.getPets());
    }
    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }
}
