package org.khube.main.controller;

import org.khube.main.dto.CompanyDto;
import org.khube.main.mapper.CompanyMapper;
import org.khube.main.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCompany(@RequestBody CompanyDto companyDto) {
        if (companyDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Company cannot be null");

        }

        try {
            var company = companyService.createCompany(companyDto);
            return ResponseEntity.status(HttpStatusCode.valueOf(201))
                                 .body(CompanyMapper.mapToDto(company));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .header("Error", "Error creating company")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body("Error creating company: " + e.getMessage());
        }
    }

    public ResponseEntity<List<CompanyDto>> fetchAllCompanies() {
        List<CompanyDto> companyList = companyService.fetchAllCompanies();
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }
}
