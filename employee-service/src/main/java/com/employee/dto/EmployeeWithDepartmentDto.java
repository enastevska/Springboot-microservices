package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto which stores the employee and department data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWithDepartmentDto {

    private EmployeeDto employee;
    private DepartmentDto department;
}
