package com.example.demo.Repositories;

import com.example.demo.Entities.Company;
import com.example.demo.Enums.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByCompanyTypeAndActiveTrue(CompanyType companyType);
}
