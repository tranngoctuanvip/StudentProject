package com.thuanthanh.StudentProject.Config;

import com.thuanthanh.StudentProject.Entity.Role;
import com.thuanthanh.StudentProject.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(roleRepository.findByRole("admin").isEmpty()){
            roleRepository.save(new Role("admin"));
        }
        if(roleRepository.findByRole("user").isEmpty()){
            roleRepository.save(new Role("user"));
        }
        if(roleRepository.findByRole("author").isEmpty()){
            roleRepository.save(new Role("author"));
        }
        if(roleRepository.findByRole("edithor").isEmpty()){
            roleRepository.save(new Role("edithor"));
        }
    }
}
