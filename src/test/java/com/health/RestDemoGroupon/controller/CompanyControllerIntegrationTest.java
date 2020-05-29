package com.health.RestDemoGroupon.controller;

import com.health.RestDemoGroupon.RestDemoGrouponApplication;
import com.health.RestDemoGroupon.model.Company;
import com.health.RestDemoGroupon.service.DSCompanyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
//@WebMvcTest(value = CompanyController.class)
@SpringBootTest(classes = RestDemoGrouponApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(){
        return "http://localhost:" + port;
    }

    @Test
    public void ContextLoads(){

    }

    @Test
    public void getAllCompanies(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/companies", HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateCompany() {
        Company company = new Company();
        company.setId(1L);
        company.setName("admin");
        company.setDescription("admin");
        ResponseEntity<Company> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", company, Company.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Company company = restTemplate.getForObject(getRootUrl() + "/company/" + id, Company.class);
        assertNotNull(company);

        restTemplate.delete(getRootUrl() + "/company/" + id);

        try {
            company = restTemplate.getForObject(getRootUrl() + "/company/" + id, Company.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
