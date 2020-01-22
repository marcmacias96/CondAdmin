package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IMonthlyAccounts extends CrudRepository<MonthlyAccounts, Integer> {
	@Query("SELECT MONTHLY FROM MonthlyAccounts MONTHLY WHERE MONTHLY.month =:month AND MONTHLY.annualCounts.idannualcounts= :id")
    public MonthlyAccounts findByMonth(Integer month, Integer id);
}
