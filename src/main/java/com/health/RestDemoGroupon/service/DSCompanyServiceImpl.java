package com.health.RestDemoGroupon.service;

import com.health.RestDemoGroupon.model.Company;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DSCompanyServiceImpl implements CompanyService {

    private static final AtomicLong counter = new AtomicLong();

    static Map<Long, Company> companies =  new HashMap<>();

    static {
        companies =  populateDummyUsers();
    }

    @Override
    public Company findById(long id) {
        return companies.get(id);
    }

    @Override
    public List<Company> findAllCompany() {
        List<Company> allCompanies =  new ArrayList<>(companies.values());
        //allCompanies.sort(Comparator.comparing(Company::getName));
        allCompanies.sort((a,b)-> a.getName().compareTo(b.getName()));
        return allCompanies;
    }

    @Override
    public void saveCompany(Company company) {
        companies.put(company.getId(), company);
    }

    @Override
    public void updateCompany(Company company) {
        Long key = company.getId();
        companies.put(key, company);
    }

    @Override
    public void deleteCompanyById(long id) {
        companies.remove(id);
    }

    private static Map<Long, Company> populateDummyUsers(){
        Map<Long, Company> dumpData =  new HashMap<>();
        dumpData.put(1L, new Company(counter.incrementAndGet(),"Groupon’s Health", "healths"));
        dumpData.put(2L, new Company(counter.incrementAndGet(),"Beauty","Beauty description"));
        dumpData.put(3L, new Company(counter.incrementAndGet(),"Wellness","Wellness description"));

        dumpData.put(4L, new Company(counter.incrementAndGet(),"Groupon’s Health", "Groupon's Health description"));
        dumpData.put(5L, new Company(counter.incrementAndGet(),"Beauty","Beauty description"));
        dumpData.put(6L, new Company(counter.incrementAndGet(),"Wellness","Wellness description"));

        return dumpData;
    }
}
