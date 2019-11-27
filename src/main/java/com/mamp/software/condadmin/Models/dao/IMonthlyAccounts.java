package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;
import org.springframework.data.repository.CrudRepository;

public interface IMonthlyAccounts extends CrudRepository<MonthlyAccounts, Integer> {
}
