package com.crossover.techtrial.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.mapper.DailyElectricityAccess;

@Service
public class DailyElectricityServiceImpl implements DailyElectricityService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DailyElectricityAccess dailyElectricityAccess;

    public List<DailyElectricity> allDailyElectricityFromYesterday(Long panelId) {
        return dailyElectricityAccess.getDailyElectricityFromSproc(panelId);

    }

}