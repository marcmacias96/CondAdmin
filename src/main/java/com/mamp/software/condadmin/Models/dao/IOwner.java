package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.House;
import com.mamp.software.condadmin.Models.entities.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOwner extends CrudRepository<Owner, Integer> {
    @Query("SELECT HOU FROM Owner HOU WHERE HOU.condominium.idcondominium = :id")
    public List<Owner> findByCondom(Integer id);
}
