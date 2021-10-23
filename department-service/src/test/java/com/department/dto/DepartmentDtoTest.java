package com.department.dto;

/**
 * Create {@link DepartmentDto} needed for testing.
 */
public class DepartmentDtoTest {

    public static DepartmentDto departmentDto() {

        DepartmentDto department = new DepartmentDto();
        department.setDepartmentName("Department name");
        department.setDepartmentCode("code");
        department.setDepartmentAddress("address");

        return department;
    }
}