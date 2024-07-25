package com.magure.crud.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Employee Details")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
}
