package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.Role;
import com.mamp.software.condadmin.Models.entities.USer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUser dao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        USer user = dao.findByName(userName);
        if(user == null){
            throw new UsernameNotFoundException("Usuario no encontrado" + userName);
        }

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        for (Role role: user.getRoleList() ) {
            roles.add(new SimpleGrantedAuthority((role.getName())));
        }

        if(roles.isEmpty()){
            throw new UsernameNotFoundException("Usuario no tiene roles asignados");
        }
        return new User(user.getName(),user.getPassword(), true,true,true,true, roles);
    }

    public USer findbyName(String name){
        return dao.findByName(name);
    }

    @Transactional
    public void save(USer uSer) {
        dao.save(uSer);
    }
}
