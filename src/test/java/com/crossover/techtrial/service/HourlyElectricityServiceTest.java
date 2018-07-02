package com.crossover.techtrial.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.repository.HourlyElectricityRepository;

public class HourlyElectricityServiceTest {

    @InjectMocks
    HourlyElectricityServiceImpl hourlyElectricityService;

    @Autowired
    HourlyElectricity hourlyElectricity;

    @Autowired
    Panel panel;

    @Mock
    HourlyElectricityRepository hourlyElectricityRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        panel = new Panel();
        panel.setBrand("Testing");
        panel.setId(1L);
        panel.setSerial("123456789");
        panel.setLatitude((double) 123456789);
        panel.setLongitude((double) 1234567899);

        hourlyElectricity = new HourlyElectricity();
        hourlyElectricity.setId(1L);
        hourlyElectricity.setReadingAt(LocalDateTime.now().minusDays(1L));
        hourlyElectricity.setGeneratedElectricity(100L);
        hourlyElectricity.setPanel(panel);
    }

    @Test
    public void saveHourlyElectricty() {
        // Given
        when(hourlyElectricityRepository.save(any(HourlyElectricity.class))).thenReturn(hourlyElectricity);

        // When
        HourlyElectricity result = hourlyElectricityService.save(hourlyElectricity);

        // Then
        Assert.assertEquals(hourlyElectricity.getId(), result.getId());
        Assert.assertEquals(hourlyElectricity.getGeneratedElectricity(), result.getGeneratedElectricity());
        Assert.assertEquals(hourlyElectricity.getReadingAt(), result.getReadingAt());

        Assert.assertEquals(hourlyElectricity.hashCode(),result.hashCode());
        Assert.assertEquals(hourlyElectricity.getPanel().equals(result.getPanel()),true);
        Assert.assertEquals(hourlyElectricity.getPanel().hashCode(),result.getPanel().hashCode());

        Assert.assertEquals(hourlyElectricity.getPanel().getBrand(), result.getPanel().getBrand());
        Assert.assertEquals(hourlyElectricity.getPanel().getSerial(), result.getPanel().getSerial());
        Assert.assertEquals(hourlyElectricity.getPanel().getLatitude(), result.getPanel().getLatitude());
        Assert.assertEquals(hourlyElectricity.getPanel().getLongitude(), result.getPanel().getLongitude());

        Assert.assertEquals(hourlyElectricity.toString(), result.toString());
        Assert.assertEquals(hourlyElectricity.getPanel().toString(), result.getPanel().toString());
        verify(hourlyElectricityRepository).save(any(HourlyElectricity.class));
    }

}
