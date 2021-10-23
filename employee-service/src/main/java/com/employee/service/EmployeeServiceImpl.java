package com.employee.service;

import com.employee.dto.DepartmentDto;
import com.employee.dto.EmployeeDto;
import com.employee.dto.EmployeeWithDepartmentDto;
import com.employee.entity.Employee;
import com.employee.mapper.EmployeeMapper;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;

/**
 * Implementation of {@link EmployeeService}.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String DEPARTMENT_SERVICE_URL = "http://DEPARTMENT-SERVICE/departments/";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Employee saveEmployee(EmployeeDto employee) {

        return employeeRepository.save(EmployeeMapper.toEmployee(employee));
    }

    public Employee updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Data for given employee id '%s' not found", employeeId)));
        return employeeRepository.save(EmployeeMapper.assembleEmployee(employee, employeeDto));
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Data for given employee id '%s' not found", employeeId)));
        employeeRepository.delete(employee);
    }

    public EmployeeWithDepartmentDto getEmployeeWithDepartment(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Data for given employee id '%s' not found", employeeId)));

        DepartmentDto departmentDto =
                restTemplate.getForObject(DEPARTMENT_SERVICE_URL + employee.getDepartmentId(), DepartmentDto.class);

        return new EmployeeWithDepartmentDto(EmployeeMapper.toEmployeeDto(employee), departmentDto);
    }
}
