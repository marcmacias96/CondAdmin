package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.USer;
import org.springframework.data.repository.CrudRepository;

public interface IUser extends CrudRepository<USer, Integer> {

    public USer findByName(String name);
}
