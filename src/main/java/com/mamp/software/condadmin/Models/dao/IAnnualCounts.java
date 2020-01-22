package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.Condominium;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAnnualCounts extends CrudRepository<AnnualCounts, Integer> {
	@Query("SELECT ANUAL FROM AnnualCounts ANUAL WHERE ANUAL.year =:year")
    public AnnualCounts findByYear(Integer year);

    @Query("SELECT ANUAL FROM AnnualCounts ANUAL WHERE ANUAL.condominium.idcondominium =:id")
    public List<AnnualCounts> findByCondom(Integer id);
}
