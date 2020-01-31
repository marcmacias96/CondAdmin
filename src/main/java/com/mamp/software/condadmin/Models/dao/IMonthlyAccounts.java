package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMonthlyAccounts extends CrudRepository<MonthlyAccounts, Integer> {
	@Query("SELECT MONTHLY FROM MonthlyAccounts MONTHLY WHERE MONTHLY.month =:month AND MONTHLY.annualCounts.idannualcounts= :id")
    public MonthlyAccounts findByMonth(Integer month, Integer id);

    @Query("SELECT MONTHLY FROM MonthlyAccounts MONTHLY WHERE  MONTHLY.annualCounts.idannualcounts= :id")
    public List<MonthlyAccounts> findByYear( Integer id);


}
