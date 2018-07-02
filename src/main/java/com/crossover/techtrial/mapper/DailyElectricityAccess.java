package com.crossover.techtrial.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crossover.techtrial.dto.DailyElectricity;

@Component
public class DailyElectricityAccess {

    @Autowired
    DailyElectricitySproc dailyElectricitySproc;

    public List<DailyElectricity> getDailyElectricityFromSproc(Long id) {

        final Map<String, Object> result = dailyElectricitySproc.execute(id);

            return (List<DailyElectricity>) result.get(dailyElectricitySproc.ATTRIBUTE);
    }
}
