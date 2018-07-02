package com.crossover.techtrial.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.repository.PanelRepository;

public class PanelServiceTest {
    @Autowired
    Panel panel;

    @InjectMocks
    PanelServiceImpl panelService;

    @Mock
    PanelRepository panelRepository;
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        panel = new Panel();
        panel.setBrand("Testing");
        panel.setId(1L);
        panel.setSerial("123456789");
        panel.setLatitude((double) 123456789);
        panel.setLongitude((double) 1234567899);
    }

    @Test
    public void saveHourlyElectricty() {
        // Given
        when(panelRepository.findBySerial(anyString())).thenReturn(panel);

        // When
        Panel result = panelService.findBySerial(anyString());
        Assert.assertEquals(panel.getLongitude(), result.getLongitude());
        Assert.assertEquals(panel.getLatitude(), result.getLatitude());
        Assert.assertEquals(panel.getSerial(), result.getSerial());
        Assert.assertEquals(panel.getBrand(), result.getBrand());
        Assert.assertEquals(panel.toString(), result.toString());


        verify(panelRepository).findBySerial(anyString());
    }

    }
