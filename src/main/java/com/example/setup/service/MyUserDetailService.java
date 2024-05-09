package com.example.setup.service;

import com.example.setup.respository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.example.setup.config.Constants.ADMIN_ROLE;
import static com.example.setup.config.Constants.USER_ROLE;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDetailRepository.getByUsername(username);
        if(user.isPresent()){
            return User.builder()
                    .username(user.get().getUsername())
                    .password(user.get().getPassword())
                    .roles(getRole(user.get().getRole()))
                    .build();
        }else{
            throw new UsernameNotFoundException("User not Found");
        }
    }
    public String[] getRole(String role){
        if(Objects.equals(role,ADMIN_ROLE)){
            return new String[]{ADMIN_ROLE,USER_ROLE};
        }
        return new String[]{USER_ROLE};
    }
}
