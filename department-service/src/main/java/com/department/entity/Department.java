package com.department.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Department entity.
 */
@Entity
@Table(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "department_address_")
    private String departmentAddress;
    @Column(name = "department_code_")
    private String departmentCode;
}
