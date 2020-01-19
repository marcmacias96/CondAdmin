package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.House;
import com.mamp.software.condadmin.Models.entities.Income;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IIncome extends CrudRepository<Income, Integer> {
	@Query("SELECT INC FROM Income INC WHERE INC.condominium.idcondominium = :id")
    public List<Income> findByCondom(Integer id);

    @Query("SELECT INC FROM Income INC WHERE INC.house.idhouse = :id AND INC.state=:FALSE")
    public List<Income> findByHouse(Integer id);
}
