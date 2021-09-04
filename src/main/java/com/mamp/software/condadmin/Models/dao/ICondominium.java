package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICondominium extends CrudRepository<Condominium, Integer> {
    @Query("SELECT COND FROM Condominium COND WHERE COND.uSer.idUser = :id")
    public Condominium findByUser(Integer id);
}
