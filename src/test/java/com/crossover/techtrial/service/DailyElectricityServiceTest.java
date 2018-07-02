package com.crossover.techtrial.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.mapper.DailyElectricityAccess;

public class DailyElectricityServiceTest {
    @InjectMocks
    DailyElectricityServiceImpl dailyElectricityService;

    @Autowired
    DailyElectricity dailyElectricity;

    @Mock
    DailyElectricityAccess dailyElectricityAccess;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        dailyElectricity = new DailyElectricity();
        dailyElectricity.setDate(Date.valueOf(LocalDate.now().minusDays(1L)));
        dailyElectricity.setAverage(1D);
        dailyElectricity.setSum(1L);
        dailyElectricity.setMin(1L);
        dailyElectricity.setMax(1L);
    }

    @Test
    public void saveHourlyElectricty() {
        List<DailyElectricity> dailyElectricityList = new ArrayList<>();
        dailyElectricityList.add(dailyElectricity);
        // Given
        when(dailyElectricityAccess.getDailyElectricityFromSproc(anyLong())).thenReturn(dailyElectricityList);

        // When
        List<DailyElectricity> result = dailyElectricityService.allDailyElectricityFromYesterday(anyLong());

        // Then
        Assert.assertEquals(dailyElectricityList.get(0).getDate(), result.get(0).getDate());
        Assert.assertEquals(dailyElectricityList.get(0).getAverage(), result.get(0).getAverage());
        Assert.assertEquals(dailyElectricityList.get(0).getMin(), result.get(0).getMin());
        Assert.assertEquals(dailyElectricityList.get(0).getMax(), result.get(0).getMax());



        verify(dailyElectricityAccess).getDailyElectricityFromSproc(anyLong());
    }
}
