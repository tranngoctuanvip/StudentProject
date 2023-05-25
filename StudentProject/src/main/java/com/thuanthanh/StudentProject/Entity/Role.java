package com.thuanthanh.StudentProject.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "role")
    private String role;

    public Role(String role) {
        this.role=role;
    }

    public Role() {

    }
}
