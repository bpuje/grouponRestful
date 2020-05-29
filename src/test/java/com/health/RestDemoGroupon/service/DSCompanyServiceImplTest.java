package com.health.RestDemoGroupon.service;

import com.health.RestDemoGroupon.model.Company;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
//@WebMvcTest(value = DSCompanyServiceImpl.class)
public class DSCompanyServiceImplTest {

//    @Autowired
//    private MockMvc mockMvc;

//    @MockBean
//    private DSCompanyServiceImpl companyService;

    @Mock
    CompanyService companyService;

    @InjectMocks
    DSCompanyServiceImpl dsCompanyService;

    @Test
    public void findAllCompanyTest(){
        when(companyService.findAllCompany()).thenReturn(Stream
                .of(new Company(1L, "Danile", "USA"), new Company(2L, "Huy",  "UK")).collect(Collectors.toList()));
        assertEquals(2, companyService.findAllCompany().size());
    }

//    @Test
//    public void saveEmployeeTest(){
//        Company mockCompany = new Company(999L, "John",  "Doe");
////        Mockito.when(companyService.saveCompany(Mockito.any(Company.class))).thenReturn(mockCompany);
//        when(companyService.saveCompany()).thenReturn(mockCompany);
//        assertEquals(2, companyService.saveCompany(mockCompany));


//        DSCompanyServiceImpl dsCompanyService;
//        Company company;
//
//        @Before
//        public void setupMock() {
//            company = mock(Company.class);
//            dsCompanyService = mock(DSCompanyServiceImpl.class);
//        }
//
//        @Test
//        public void testMockCreation(){
//            assertNotNull(company);
//            assertNotNull(companyService);
//        }
//    }

}
