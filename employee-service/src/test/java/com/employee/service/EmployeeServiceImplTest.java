package com.employee.service;

import com.employee.dto.DepartmentDto;
import com.employee.dto.EmployeeWithDepartmentDto;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static com.employee.dto.EmployeeDtoTest.employeeDto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link EmployeeServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void shouldSaveEmployee() {
        // given

        // when
        employeeService.saveEmployee(employeeDto());

        // then
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldUpdateEmployee() {
        // given
        Long id = 1L;
        Employee employee = mock(Employee.class);
        given(employeeRepository.findById(id)).willReturn(Optional.of(employee));

        // when
        employeeService.updateEmployee(id, employeeDto());

        // then
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenUpdatingNonExistingEmployee() {
        // given
        Long id = 1L;
        willThrow(EntityNotFoundException.class).given(employeeRepository).findById(id);

        // when
        Executable executable = () -> employeeService.updateEmployee(id, employeeDto());

        // then
        assertThrows(EntityNotFoundException.class, executable);
    }

    @Test
    void shouldDeleteEmployee() {
        // given
        Long id = 1L;
        Employee employee = mock(Employee.class);
        given(employeeRepository.findById(id)).willReturn(Optional.of(employee));

        // when
        employeeService.deleteEmployee(id);

        // then
        verify(employeeRepository).delete(any(Employee.class));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenDeletingNonExistingEmployee() {
        // given
        Long id = 1L;
        willThrow(EntityNotFoundException.class).given(employeeRepository).findById(id);

        // when
        Executable executable = () -> employeeService.deleteEmployee(id);

        // then
        assertThrows(EntityNotFoundException.class, executable);
    }

    @Test
    void shouldGetEmployeeWithDepartment() {
        // given
        Long id = 1L;
        Employee employee = mock(Employee.class);
        DepartmentDto department = mock(DepartmentDto.class);
        given(employeeRepository.findById(id)).willReturn(Optional.of(employee));
        given(restTemplate.getForObject(anyString(), any())).willReturn(department);

        // when
        EmployeeWithDepartmentDto result = employeeService.getEmployeeWithDepartment(id);

        // then
        assertThat(result.getDepartment(), Matchers.is(Matchers.notNullValue()));
        assertThat(result.getEmployee(), Matchers.is(Matchers.notNullValue()));
    }
}
