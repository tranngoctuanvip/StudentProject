package com.thuanthanh.StudentProject.Security.UserPrincal;

import com.thuanthanh.StudentProject.Entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(String email, String password, Collection<? extends GrantedAuthority> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrinciple build(Customer customer){
        List<GrantedAuthority> authorities = customer.getRoleSet().stream().map(role ->
                new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
        return new UserPrinciple(customer.getEmail(), customer.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
