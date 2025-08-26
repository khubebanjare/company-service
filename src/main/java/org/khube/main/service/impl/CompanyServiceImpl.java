package org.khube.main.service.impl;

import jakarta.transaction.Transactional;
import org.khube.main.dto.CompanyDto;
import org.khube.main.dto.EmployeeDto;
import org.khube.main.entity.Company;
import org.khube.main.mapper.CompanyMapper;
import org.khube.main.repository.CompanyRepository;
import org.khube.main.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final WebClient.Builder webClientBuilder;

    @Value("${employee.service.url}")
    private String employeeServiceUrl;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, WebClient.Builder webClientBuilder) {
        this.companyRepository = companyRepository;
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Creates a new company.
     *
     * @param companyDto the company to create
     * @return the created company
     */
    @Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRED)
    @Override
    public Company createCompany(CompanyDto companyDto) {
        EmployeeDto employeeDto = webClientBuilder.build()
                .get()
                .uri(employeeServiceUrl + "/api/employee/findById/" + companyDto.getEmployee().getEmpId())
                .retrieve()
                .bodyToMono(EmployeeDto.class)
                .block();

        companyDto.setEmployee(employeeDto);
        Company company = CompanyMapper.mapToEntity(companyDto);

        return companyRepository.save(company);
    }

    /**
     * Retrieves all companies from the repository.
     *
     * @return a list of CompanyDto containing all companies
     */
    @Override
    public List<CompanyDto> fetchAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyMapper::mapToDto)
                .toList();
    }
}
