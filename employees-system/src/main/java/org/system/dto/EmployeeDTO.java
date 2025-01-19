package org.system.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    /**
     * the employee id
     */
    @NotBlank(message = "Employee ID is mandatory")
    @UniqueElements(message = "Employee ID is unique ")
    private String employeeId;
    /**
     * The employee full name
     */
    @NotBlank(message = "Employee full name is mandatory")
    private String fullName;
    /**
     * the employee job title
     */
    @NotBlank(message = "Employee job title is mandatory")
    private String jobTitle;
    /**
     * the employee hire date
     */
    @NotBlank(message = "Employee hire date is mandatory")
    private String hireDate;
    /**
     * the employee employment status
     */
    @NotBlank(message = "Employee employment status is mandatory")
    private String employmentStatus;
    /**
     * the employee address
     */
    @NotBlank(message = "Employee address is mandatory")
    private String address;
    /**
     * the employee department
     */
    @NotBlank(message = "Employee department is mandatory")
    private String department;

    /**
     * the employee contact information.
     */
    private ContactInformationDTO contactInformation;
}
