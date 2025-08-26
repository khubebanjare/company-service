package org.khube.main.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyDto {

    private String compName;

    private EmployeeDto employee;
}
