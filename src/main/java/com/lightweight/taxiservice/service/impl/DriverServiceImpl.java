package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.DriverRepository;
import com.lightweight.taxiservice.DTO.driver.ConverterDriverDTO;
import com.lightweight.taxiservice.DTO.driver.DriverUpdateDTO;
import com.lightweight.taxiservice.entity.Car;
import com.lightweight.taxiservice.entity.Driver;
import com.lightweight.taxiservice.exception.NoDriverFoundException;
import com.lightweight.taxiservice.exception.NoDriversFoundException;
import com.lightweight.taxiservice.service.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;
    private ConverterDriverDTO converterDriverDTO;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository,
                             ConverterDriverDTO converterDriverDTO) {
        this.driverRepository = driverRepository;
        this.converterDriverDTO = converterDriverDTO;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = driverRepository.findAll();
        if (!drivers.isEmpty()) {
            return drivers;
        } else {
            throw new NoDriversFoundException("There are no drivers exist");
        }
    }

    @Override
    public Driver findById(Long id) {
        isDatabaseEmpty();
        return driverRepository.findById(id)
                .orElseThrow(() -> new NoDriverFoundException("Driver not found with id: " + id));
    }

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver update(Long id, DriverUpdateDTO updateDriverDTO) {
        isDatabaseEmpty();
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new NoDriverFoundException("Impossible to update the Driver. Driver not found with id: " + id));
        Driver updatedDriver = converterDriverDTO.convertToEntity(updateDriverDTO, driver);

        return driverRepository.save(updatedDriver);
    }


    @Override
    public void deleteById(Long id) {
        isDatabaseEmpty();
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new NoDriverFoundException("Impossible to delete the Driver. Driver not found with id: " + id));

        Car car = driver.getCar();
        if (car != null) {
            car.setDriver(null);
        }
        driverRepository.delete(driver);
    }

    @Override
    public List<Driver> findDriversWithoutCars() {
        return driverRepository.findDriversWithoutCars();
    }

    @Override
    public Page<Driver> getAllDriversSorted(int page, int size, String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);

        if ("desc".equals(sortOrder)) {
            sort = sort.descending();
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return driverRepository.findAll(pageRequest);
    }

    @Override
    public Page<Driver> findByFieldContainingIgnoreCase(String fieldName, String searchValue, Pageable pageable) {
        return driverRepository.findByFieldContainingIgnoreCase(fieldName, searchValue, pageable);
    }

    @Override
    public List<Driver> findAvailableForOrderDrivers() {
        return driverRepository.findAvailableForOrderDrivers();
    }

    private void isDatabaseEmpty() {
        driverRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new NoDriverFoundException("Data base has not any records of drivers"));
    }
}
