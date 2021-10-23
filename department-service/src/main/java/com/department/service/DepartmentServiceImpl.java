package com.department.service;

import com.department.dto.DepartmentDto;
import com.department.entity.Department;
import com.department.mapper.DepartmentMapper;
import com.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Implementation of {@link DepartmentService}.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(DepartmentDto department) {
        return departmentRepository.save(DepartmentMapper.toDepartment(department));
    }

    public Department findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Data for given department id '%s' not found", departmentId)));
    }
}
