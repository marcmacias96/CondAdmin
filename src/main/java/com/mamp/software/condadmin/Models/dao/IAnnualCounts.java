package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.Condominium;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAnnualCounts extends CrudRepository<AnnualCounts, Integer> {
	@Query("SELECT ANUAL FROM AnnualCounts ANUAL WHERE ANUAL.year =:year AND ANUAL.condominium.idcondominium =:Id")
    public AnnualCounts findByYear(Integer year, Integer Id);

    @Query("SELECT ANUAL FROM AnnualCounts ANUAL WHERE ANUAL.condominium.idcondominium =:Id")
    public List<AnnualCounts> findByCondom(Integer Id);
}
