package com.slit.pointofsales.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class DAOUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_address")
    private String userAddress;
    private String province;
    private String district;
    private String tp;
    private String nic;
    private String email;
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "profile_image")
    private byte[] profileImage;




}
