package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.Expenses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IExpenses extends CrudRepository<Expenses, Integer> {
    @Query("SELECT EXP FROM Expenses EXP WHERE EXP.condominium.idcondominium = :id")
    public List<Expenses> findByCondom(Integer id);
}
