package com.crossover.techtrial.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.service.HourlyElectricityService;
import com.crossover.techtrial.service.PanelService;


/**
 * PanelControllerTest class will test all APIs in PanelController.java.
 * @author Crossover
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PanelControllerTest {
  
  MockMvc mockMvc;

  List<HourlyElectricity> hourlyElectricities = new ArrayList<>();

  
  @Mock
  private PanelController panelController;
  
  @Autowired
  private TestRestTemplate template;

  @Mock
  HourlyElectricityService hourlyElectricityService;

  @Mock
  PanelService panelService;

  Panel panel = new Panel();

  HourlyElectricity hourlyElectricity = new HourlyElectricity();

  @Before
  public void setup() throws Exception {

    mockMvc = MockMvcBuilders.standaloneSetup(panelController).build();

    panel.setSerial("1234567890");
    panel.setLongitude((double) 1231467);
    panel.setId(1L);
    panel.setSerial("1234567");

    hourlyElectricity = new HourlyElectricity();
    hourlyElectricity.setPanel(panel);
    hourlyElectricity.setGeneratedElectricity(100L);
    hourlyElectricity.setReadingAt(LocalDateTime.now().minusDays(2));
    this.hourlyElectricities.add(hourlyElectricityService.save(hourlyElectricity));
  }

  /*PANEL SECTION*/
  @Test
  public void testPanelShouldBeRegistered() throws Exception {
    HttpEntity<Object> panel = getHttpEntity(
            "{\"serial\": \"232323\", \"longitude\": \"54.123232\","
                    + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
    ResponseEntity<Panel> response = template.postForEntity(
            "/api/register", panel, Panel.class);
    Assert.assertEquals(202,response.getStatusCode().value());
  }

  @Test
  public void testPanelShouldntBeRegistered() throws Exception {
    HttpEntity<Object> panel = getHttpEntity(
            "{\"serial\": \"232323\", \"longitude\": \"54.123232\","
                    + " \"latitude\": \"54.123232\", }");
    ResponseEntity<Panel> response = template.postForEntity(
            "/api/register", panel, Panel.class);
    Assert.assertEquals(400,response.getStatusCode().value());
  }//////////////////////////////////////////////


  @Test
  public void testHourlyElectricityShouldntBeRegistered() throws Exception {
    HttpEntity<Object> hourlyElectricity = getHttpEntity(
            "{\n" +
                    "\"id\" : 1,\n" +
                    "}");
    ResponseEntity<HourlyElectricity> response = template.postForEntity(
            "/api/panels/1232131231231/hourly",  hourlyElectricity, HourlyElectricity.class);
    System.out.println(response.getBody().toString());
    Assert.assertEquals(400, response.getStatusCode().value());
  }


  @Test
  public void testHourlyElectricityShouldntBeGetted() throws Exception {
    ResponseEntity<HourlyElectricity> response = template.getForEntity(
            "/api/panels/123456543212345/hourly",   HourlyElectricity.class);
    Assert.assertEquals(200, response.getStatusCode().value());
  }

  @Test
  public void testDailyElectricityShouldntBeGetted() throws Exception {
    List<DailyElectricity> dailyElectricityForPanel = new ArrayList<>();
    ResponseEntity<? extends List> response = template.getForEntity(
            "/api/panels/123456543212345/daily", dailyElectricityForPanel.getClass() );
    Assert.assertEquals(200, response.getStatusCode().value());
  }


  private HttpEntity<Object> getHttpEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<Object>(body, headers);
  }

}
