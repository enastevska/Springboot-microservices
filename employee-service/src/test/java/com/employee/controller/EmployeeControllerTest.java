package com.employee.controller;

import com.employee.dto.DepartmentDto;
import com.employee.dto.EmployeeDto;
import com.employee.dto.EmployeeDtoTest;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.employee.dto.EmployeeDtoTest.employeeDto;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for {@link EmployeeController}.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void shouldSaveEmployee() throws Exception {

        // given
        // when
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeDto())))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Elena")))
                .andExpect(jsonPath("$.lastName", equalTo("Nastevska")))
                .andExpect(jsonPath("$.email", equalTo("test@hotmail.com")));
    }

    @Test
    void shouldUpdateEmployee() throws Exception {

        // given
        String firstName = "Elena";
        String lastName = "Nastevska";
        String email = "test@hotmail.com";
        Long departmentId = 1L;

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeDto())))
                .andExpect(status().isCreated());

        String newMail = "newMail@gmail.com";
        EmployeeDto updatedEmployee = new EmployeeDto();
        updatedEmployee.setFirstName(firstName);
        updatedEmployee.setLastName(lastName);
        updatedEmployee.setEmail(newMail);
        updatedEmployee.setDepartmentId(departmentId);

        List<Employee> savedEmployee = employeeRepository.findAll();

        // when
        mockMvc.perform(put("/employees/" + savedEmployee.get(0).getEmployeeId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedEmployee)))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(firstName)))
                .andExpect(jsonPath("$.lastName", equalTo(lastName)))
                .andExpect(jsonPath("$.email", equalTo(newMail)));
    }

    @Test
    void shouldGetEmployeeWithDepartment() throws Exception {

        // given
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeDto())))
                .andExpect(status().isCreated());

        List<Employee> savedEmployee = employeeRepository.findAll();

        String name = "Department name";
        String code = "code";
        String address = "address";
        DepartmentDto department = new DepartmentDto();
        department.setDepartmentName(name);
        department.setDepartmentCode(code);
        department.setDepartmentAddress(address);

        when(restTemplate.getForObject(anyString(), any())).thenReturn(department);

        // when
        mockMvc.perform(get("/employees/" + savedEmployee.get(0).getEmployeeId()))
                .andDo(MockMvcResultHandlers.print())
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee.firstName", equalTo("Elena")))
                .andExpect(jsonPath("$.employee.lastName", equalTo("Nastevska")))
                .andExpect(jsonPath("$.employee.email", equalTo("test@hotmail.com")))
                .andExpect(jsonPath("$.department.departmentName", equalTo(name)))
                .andExpect(jsonPath("$.department.departmentCode", equalTo(code)))
                .andExpect(jsonPath("$.department.departmentAddress", equalTo(address)));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {

        // given
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeDto())))
                .andExpect(status().isCreated());

        List<Employee> savedEmployee = employeeRepository.findAll();

        //when
        mockMvc.perform(delete("/employees/" + savedEmployee.get(0).getEmployeeId()))
                // then
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
