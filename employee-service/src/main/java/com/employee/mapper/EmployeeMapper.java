package com.employee.mapper;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import org.springframework.stereotype.Component;

/**
 * Mapper class for {@link Employee} and {@link EmployeeDto}.
 */
@Component
public final class EmployeeMapper {

    private EmployeeMapper() {

    }

    /**
     * Create entity object out of dto.
     *
     * @param employeeDto the dto to be mapped
     * @return {@link Employee}
     */
    public static Employee toEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentId(employeeDto.getDepartmentId());

        return employee;
    }

    /**
     * Map {@link EmployeeDto} to {@link Employee}.
     *
     * @param employee    .
     * @param employeeDto .
     * @return {@link Employee}
     */
    public static Employee assembleEmployee(Employee employee, EmployeeDto employeeDto) {
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentId(employeeDto.getDepartmentId());

        return employee;
    }

    /**
     * Create dto out of entity.
     *
     * @param employee {@link Employee}
     * @return the dto
     */
    public static EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartmentId(employee.getDepartmentId());

        return employeeDto;
    }
}
