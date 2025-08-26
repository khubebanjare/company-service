package org.khube.main.mapper;

import org.khube.main.dto.CompanyDto;
import org.khube.main.entity.Company;

public class CompanyMapper {

    private CompanyMapper() {
        // Private constructor to prevent instantiation
    }

    public static Company mapToEntity(CompanyDto dto) {
        if (dto == null) {
            return null;
        }
        Company entity = new Company();
        entity.setCompName(dto.getCompName());
        return entity;
    }

    public static CompanyDto mapToDto(Company entity) {
        if (entity == null) {
            return null;
        }
        CompanyDto dto = new CompanyDto();
        dto.setCompName(entity.getCompName());
        return dto;
    }
}
