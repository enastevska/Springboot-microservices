package com.department.controller;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for {@link Department} related actions.
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department saveDepartment(@RequestBody DepartmentDto department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public Department findDepartmentById(@PathVariable("departmentId") Long departmentId) {
        return departmentService.findDepartmentById(departmentId);
    }

}
