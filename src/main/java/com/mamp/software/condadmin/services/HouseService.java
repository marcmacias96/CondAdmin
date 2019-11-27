package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IHouse;
import com.mamp.software.condadmin.Models.entities.House;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HouseService implements IHouseService {
    @Autowired
    private IHouse dao;

    @Override
    public void save(House house) {
        dao.save(house);
    }

    @Override
    public House findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<House> findAll() {
        return (List<House>) dao.findAll();
    }
}
