package com.department.mapper;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import org.springframework.stereotype.Component;

/**
 * Mapper class for {@link Department}.
 */
@Component
public final class DepartmentMapper {

    private DepartmentMapper() {

    }

    /**
     * Create entity object out of dto.
     *
     * @param departmentDto {@link DepartmentDto}
     * @return {@link Department}
     */
    public static Department toDepartment(DepartmentDto departmentDto) {

        Department department = new Department();

        department.setDepartmentCode(departmentDto.getDepartmentCode());
        department.setDepartmentAddress(departmentDto.getDepartmentAddress());
        department.setDepartmentName(departmentDto.getDepartmentName());

        return department;
    }
}
