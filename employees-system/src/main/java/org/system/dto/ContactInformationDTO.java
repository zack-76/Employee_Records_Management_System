package org.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactInformationDTO {


    /**
     * The employee email
     */
    @Email(message = "Email should be valid")
    private String empEmail;
    /**
     * the employee phone number
     */
    @NotBlank(message = "Employee phone number is mandatory")
    private String empPhoneNumber;

    /**
     * the employee employment status
     */
    @NotBlank(message = "Employee marital status is mandatory")
    private String maritalStatus;
}
