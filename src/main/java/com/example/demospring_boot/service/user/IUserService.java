package com.example.demospring_boot.service.user;


import com.example.demospring_boot.model.entity.User;
import com.example.demospring_boot.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);
}
