package com.hillel.fedorenko.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
    private UserRole userRole;
}
