package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.Condominium;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IAnnualCounts extends CrudRepository<AnnualCounts, Integer> {
	@Query("SELECT YEAR FROM AnnualCounts ANUAL WHERE ANUAL.year =:year")
    public AnnualCounts findByYear(Integer year);
}
