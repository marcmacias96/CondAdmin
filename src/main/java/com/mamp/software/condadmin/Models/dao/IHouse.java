package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.House;
import org.springframework.data.repository.CrudRepository;

public interface IHouse extends CrudRepository<House, Integer> {
}
