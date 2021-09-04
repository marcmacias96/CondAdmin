package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IHouse;
import com.mamp.software.condadmin.Models.entities.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HouseService implements IHouseService {
    @Autowired
    private IHouse dao;

    @Override
    @Transactional
    public void save(House house) {
        dao.save(house);
    }

    @Override
    @Transactional(readOnly=true)
    public House findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<House> findAll() {
        return (List<House>) dao.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public List<House> findByOwner(Integer id) {
        return dao.findByOwner(id);
    }

    @Override
    public List<House> findByCondom(Integer id) {
        return dao.findByCondom(id);
    }


}
