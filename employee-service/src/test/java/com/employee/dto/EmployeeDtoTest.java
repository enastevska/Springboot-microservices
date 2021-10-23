package com.employee.dto;

/**
 * Create {@link EmployeeDto} needed for testing.
 */
public class EmployeeDtoTest {

    public static EmployeeDto employeeDto() {

        EmployeeDto employee = new EmployeeDto();
        employee.setFirstName("Elena");
        employee.setLastName("Nastevska");
        employee.setEmail("test@hotmail.com");
        employee.setDepartmentId(1L);

        return employee;
    }
}