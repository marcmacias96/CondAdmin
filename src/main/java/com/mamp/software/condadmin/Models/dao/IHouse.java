package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.House;
import com.mamp.software.condadmin.Models.entities.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IHouse extends CrudRepository<House, Integer> {
    @Query("SELECT HOU FROM House HOU WHERE HOU.owner.idowner = :id")
    public List<House> findByOwner(Integer id);

    @Query("SELECT HOU FROM House HOU WHERE HOU.condominium.idcondominium = :id")
    public List<House> findByCondom(Integer id);

}
