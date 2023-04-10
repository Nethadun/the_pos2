package com.slit.pointofsales.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String userAddress;
    private String province;
    private String district;
    private String tp;
    private String nic;
    private String email;
    private String userRole;
    private transient MultipartFile profileImage;

}
