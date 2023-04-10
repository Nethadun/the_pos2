package com.slit.pointofsales.service.jwt;

import com.slit.pointofsales.dto.request.UserDto;
import com.slit.pointofsales.model.DAOUser;
import com.slit.pointofsales.repository.UserRepository;
import com.slit.pointofsales.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DAOUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DAOUser save(UserDto user) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setDistrict(user.getDistrict());
        newUser.setUserAddress(user.getUserAddress());
        newUser.setTp(user.getTp());
        newUser.setUserRole(user.getUserRole());
        newUser.setEmail(user.getEmail());
        newUser.setNic(user.getNic());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setProvince(user.getProvince());
        try {
            newUser.setProfileImage(ImageUtil.compressImage(user.getProfileImage().getBytes()));
        }catch (IOException ioException){
            log.error("Failed to upload profile picture");
        }

        return userDao.save(newUser);
    }

}
