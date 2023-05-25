package com.thuanthanh.StudentProject.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "one_time_password")
    private String oneTimePassword;

    @Column(name = "otp_requested_time")
    private Date otpRequestedTime;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "customer_role",joinColumns = @JoinColumn(name = "customer_id")
            ,inverseJoinColumns = @JoinColumn(name = "role_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Role> roleSet;
}
