package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.Owner;
import org.springframework.data.repository.CrudRepository;

public interface IOwner extends CrudRepository<Owner, Integer> {
}
