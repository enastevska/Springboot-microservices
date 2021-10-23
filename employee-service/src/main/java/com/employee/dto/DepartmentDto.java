package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto representing the department information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
