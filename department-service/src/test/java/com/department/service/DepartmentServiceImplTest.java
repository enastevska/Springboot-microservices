package com.department.service;

import com.department.entity.Department;
import com.department.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static com.department.dto.DepartmentDtoTest.departmentDto;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link DepartmentServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    void shouldSaveDepartment() {
        // given

        // when
        departmentService.saveDepartment(departmentDto());

        // then
        verify(departmentRepository).save(any(Department.class));
    }

    @Test
    void shouldGetDepartmentById() {
        // given
        Long id = 1L;
        Department department = mock(Department.class);

        given(departmentRepository.findById(id)).willReturn(Optional.of(department));

        // when
        Executable executable = () -> departmentService.findDepartmentById(id);

        // then
        assertDoesNotThrow(executable);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionIfDepartmentDoesntExist() {
        // given
        Long id = 1L;
        willThrow(EntityNotFoundException.class).given(departmentRepository).findById(id);

        // when
        Executable executable = () -> departmentService.findDepartmentById(id);

        // then
        assertThrows(EntityNotFoundException.class, executable);
    }
}
