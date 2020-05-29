package com.health.RestDemoGroupon.service;

import com.health.RestDemoGroupon.model.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//@Service
public class CompanyServiceImpl implements CompanyService{

    private static final AtomicLong counter = new AtomicLong();

    private static List<Company> companies;

    static {
        companies = populateDummyUsers();
    }

    @Override
    public Company findById(long id) {
        for(Company company : companies){
            if(company.getId() == id){
                return company;
            }
        }
        return null;
    }

    @Override
    public List<Company> findAllCompany() {
        return companies;
    }

    @Override
    public void saveCompany(Company company) {
        company.setId(counter.incrementAndGet());
        companies.add(company);
    }

    @Override
    public void updateCompany(Company company) {
        int index = companies.indexOf(company);
        companies.set(index, company);
    }

    @Override
    public void deleteCompanyById(long id) {

        for(Iterator<Company> iterator = companies.iterator(); iterator.hasNext(); ){
            Company company = iterator.next();
            if(company.getId() == id){
                iterator.remove();
            }
        }
    }

    private static List<Company> populateDummyUsers(){
        List<Company> companies = new ArrayList<Company>();
        companies.add(new Company(counter.incrementAndGet(),"Groupon’s Health", "healths"));
        companies.add(new Company(counter.incrementAndGet(),"Beauty","40, 50000"));
        companies.add(new Company(counter.incrementAndGet(),"Wellness","45, 30000"));

        companies.add(new Company(counter.incrementAndGet(),"Groupon’s Health", "healths"));
        companies.add(new Company(counter.incrementAndGet(),"Beauty","40, 50000"));
        companies.add(new Company(counter.incrementAndGet(),"Wellness","45, 30000"));
        return companies;
    }

}
