package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.Condominium;
import org.springframework.data.repository.CrudRepository;

public interface ICondominium extends CrudRepository<Condominium, Integer> {
}
