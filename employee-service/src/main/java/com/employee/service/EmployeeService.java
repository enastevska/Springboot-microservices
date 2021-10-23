package com.employee.service;

import com.employee.dto.EmployeeDto;
import com.employee.dto.EmployeeWithDepartmentDto;
import com.employee.entity.Employee;

/**
 * Service to manage employees.
 */
public interface EmployeeService {

    /**
     * Save the provided employee.
     *
     * @param employee the employee to be saved
     * @return the saved employee
     */
    Employee saveEmployee(EmployeeDto employee);

    /**
     * Update the given employee with the new data.
     *
     * @param employeeId the id of the employee to be updated
     * @param employee   the new data
     * @return the updated employee
     */
    Employee updateEmployee(Long employeeId, EmployeeDto employee);

    /**
     * Delete the given employee.
     *
     * @param employeeId the id of the employee to be removed
     */
    void deleteEmployee(Long employeeId);

    /**
     * Retrieve the data for the required employee along with the department information.
     *
     * @param employeeId the employee which data should be retrieved
     * @return the employee and department information
     */
    EmployeeWithDepartmentDto getEmployeeWithDepartment(Long employeeId);
}
