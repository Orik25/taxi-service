package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.DriverRepository;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }//todo:implement exception, when there aren`t any drivers

    @Override
    public Driver findById(Long id){
        return driverRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Driver not found with id: " + id));
    }//todo:handle exception

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }//todo:implement exception, when driver doesn`t exist and send correct response(change func type to String)
}
