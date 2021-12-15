package com.example.demospring_boot.service.role;


import com.example.demospring_boot.model.entity.Role;
import com.example.demospring_boot.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}

