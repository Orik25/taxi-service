package com.lightweight.taxiservice.service.impl;

import com.lightweight.taxiservice.DAO.ArrivalCoordinatesRepository;
import com.lightweight.taxiservice.entity.ArrivalCoordinates;
import com.lightweight.taxiservice.service.interfaces.ArrivalCoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrivalCoordinatesServiceImpl implements ArrivalCoordinatesService {
    private ArrivalCoordinatesRepository coordinatesRepository;

    @Autowired
    public ArrivalCoordinatesServiceImpl(ArrivalCoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }

    @Override
    public ArrivalCoordinates findById(Long id) {
        return coordinatesRepository.findById(id).orElseThrow(()->new NullPointerException());
    }
}
