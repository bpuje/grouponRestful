package com.health.RestDemoGroupon.controller;

import com.health.RestDemoGroupon.model.Company;
import com.health.RestDemoGroupon.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/company/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllCompanies(){
        List<Company> companyList = companyService.findAllCompany();
        if(companyList.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @RequestMapping(value = "/company/", method = RequestMethod.POST)
    public ResponseEntity<?> saveCompany(@RequestBody Company company, UriComponentsBuilder ucBuilder){

        companyService.saveCompany(company);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/company/{id}").buildAndExpand(company.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCompany(@PathVariable("id") long id, @RequestBody Company company){

        Company currentCompany = companyService.findById(id);

        currentCompany.setId(company.getId());
        currentCompany.setName(company.getName());
        currentCompany.setDescription(company.getDescription());

        companyService.updateCompany(currentCompany);
        return new ResponseEntity<>(currentCompany, HttpStatus.OK);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCompany(@PathVariable("id") long id){

//        Company company = companyService.findById(id);
        companyService.deleteCompanyById(id);
        return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);

    }
}
