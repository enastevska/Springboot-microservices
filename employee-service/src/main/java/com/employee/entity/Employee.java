package com.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Employee entity.
 */
@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    @Column(name = "first_name_")
    private String firstName;
    @Column(name = "last_name_")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "department_id_")
    private Long departmentId;
}
