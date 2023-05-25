package com.thuanthanh.StudentProject.Entity.DTO;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;
@Data
public class CustomerDTO {
    private String email;
    private String password;
    private String phone;

    private Set<String> roles;
    private String type = "Bearer";
    private Collection<? extends GrantedAuthority> role;
    private String token;
}
