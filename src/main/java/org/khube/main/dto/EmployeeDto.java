package org.khube.main.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {
    private Long empId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double sal;
}
