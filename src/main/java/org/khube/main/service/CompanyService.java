package org.khube.main.service;

import org.khube.main.dto.CompanyDto;
import org.khube.main.entity.Company;

import java.util.List;

public interface CompanyService {

    Company createCompany(CompanyDto companyDto);

    List<CompanyDto> fetchAllCompanies();
}
