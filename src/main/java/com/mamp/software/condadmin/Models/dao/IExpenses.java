package com.mamp.software.condadmin.Models.dao;

import com.mamp.software.condadmin.Models.entities.Expenses;
import org.springframework.data.repository.CrudRepository;

public interface IExpenses extends CrudRepository<Expenses, Integer> {
}
