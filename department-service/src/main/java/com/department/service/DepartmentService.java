package com.department.service;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;

/**
 * Service to manage departments.
 */
public interface DepartmentService {

    /**
     * Store the provided department.
     *
     * @param department the department to be stored
     * @return the stored department
     */
    Department saveDepartment(DepartmentDto department);

    /**
     * Find department by id.
     *
     * @param departmentId the id of the department to be retrieved
     * @return the found department
     */
    Department findDepartmentById(Long departmentId);
}
