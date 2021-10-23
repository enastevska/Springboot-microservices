package com.employee.controller;

import com.employee.dto.EmployeeDto;
import com.employee.dto.EmployeeWithDepartmentDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for {@link Employee} related actions.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody EmployeeDto employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody EmployeeDto employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeWithDepartmentDto getEmployeeWithDepartment(@PathVariable("employeeId") Long employeeId) {
        return employeeService.getEmployeeWithDepartment(employeeId);
    }


}
