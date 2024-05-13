package com.example.setup.service;

import com.example.setup.object.UserDetail;
import com.example.setup.object.UserDetailDTO;
import com.example.setup.respository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailDTO signup(UserDetailDTO userDetailDTO) throws Exception{
        var username = userDetailDTO.getUsername();

        if (userDetailRepository.getByUsername(username).isPresent())
            throw new Exception("Username already exists");

        UserDetail entity = new UserDetail();
        entity.setUsername(userDetailDTO.getUsername());
        entity.setPassword(passwordEncoder.encode(userDetailDTO.getPassword()));
        entity.setRole("user");

        var savedEntity = userDetailRepository.save(entity);

        userDetailDTO.setId(savedEntity.getId());
        userDetailDTO.setRole(savedEntity.getRole());
        return userDetailDTO;
    }

    public UserDetailDTO login(UserDetailDTO userDetailDTO) throws Exception{
        var username = userDetailDTO.getUsername();
        var password = userDetailDTO.getPassword();

        var entity = userDetailRepository.getByUsername(username);

        if (entity.isEmpty())
            throw new Exception("Username or password is wrong");

        if(!passwordEncoder.matches(password,entity.get().getPassword())){
            throw new Exception("Username or password is wrong");
        }
        userDetailDTO.setPassword(entity.get().getPassword());
        userDetailDTO.setId(entity.get().getId());
        userDetailDTO.setRole(entity.get().getRole());
        return userDetailDTO;
    }
}
