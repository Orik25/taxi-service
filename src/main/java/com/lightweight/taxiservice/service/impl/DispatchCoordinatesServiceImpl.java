package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.DispatchCoordinatesRepository;
import com.lightweight.taxiservice.entity.DispatchCoordinates;
import com.lightweight.taxiservice.service.interfaces.DispatchCoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispatchCoordinatesServiceImpl implements DispatchCoordinatesService {
    private DispatchCoordinatesRepository coordinatesRepository;

    @Autowired
    public DispatchCoordinatesServiceImpl(DispatchCoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    @Override
    public DispatchCoordinates findById(Long id) {
        return coordinatesRepository.findById(id).orElseThrow(()->new NullPointerException());
    }
}
