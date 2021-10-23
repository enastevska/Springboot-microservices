package com.department.controller;

import com.department.entity.Department;
import com.department.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static com.department.dto.DepartmentDtoTest.departmentDto;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for {@link DepartmentController}.
 */
@SpringBootTest
@AutoConfigureMockMvc
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldSaveDepartment() throws Exception {

        // given

        // when
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(departmentDto())))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.departmentName", equalTo("Department name")))
                .andExpect(jsonPath("$.departmentCode", equalTo("code")))
                .andExpect(jsonPath("$.departmentAddress", equalTo("address")));
    }

    @Test
    public void shouldFindByDepartmentId() throws Exception {

        // given

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(departmentDto())))
                .andExpect(status().isCreated());

        List<Department> savedDepartment = departmentRepository.findAll();

        // when
        mockMvc.perform(get("/departments/" + savedDepartment.get(0).getDepartmentId()))
                .andDo(MockMvcResultHandlers.print())
                // then
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
