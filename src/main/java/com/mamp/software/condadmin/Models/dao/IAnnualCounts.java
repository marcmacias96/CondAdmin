package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import org.springframework.data.repository.CrudRepository;

public interface IAnnualCounts extends CrudRepository<AnnualCounts, Integer> {
}
