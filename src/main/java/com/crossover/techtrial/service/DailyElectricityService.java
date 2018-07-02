package com.crossover.techtrial.service;

import java.util.List;

import com.crossover.techtrial.dto.DailyElectricity;

public interface DailyElectricityService {

    List<DailyElectricity> allDailyElectricityFromYesterday(Long panel_id);
}
