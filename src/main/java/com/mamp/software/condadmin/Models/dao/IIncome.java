package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.Income;
import org.springframework.data.repository.CrudRepository;

public interface IIncome extends CrudRepository<Income, Integer> {
}
