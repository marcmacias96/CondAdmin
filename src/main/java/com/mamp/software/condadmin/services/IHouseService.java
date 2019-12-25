package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.House;

import java.util.List;

public interface IHouseService {
    public void save(House house);

    public House findById(Integer id);

    public void delete(Integer id);

    public List<House> findAll();

    public List<House> findByOwner(Integer id);

    public List<House> findByCondom(Integer id);
}
