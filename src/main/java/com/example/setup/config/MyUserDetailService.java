package com.example.setup.config;

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
        return userDetailRepository.getByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("User not Found"));
    }
}
