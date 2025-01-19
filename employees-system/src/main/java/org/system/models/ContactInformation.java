package org.system.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact_information")
public class ContactInformation extends BaseEntity {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The employee email
     */
    @Email(message = "Email should be valid")
    @Column(nullable = false, name = "employee_email")
    private String empEmail;
    /**
     * the employee phone number
     */
    @NotBlank(message = "Employee phone number is mandatory")
    @Column(nullable = false, name = "phone_number")
    private String empPhoneNumber;

    /**
     * the employee employment status
     */
    @NotBlank(message = "Employee marital status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "marital_status")
    private MaritalStatus maritalStatus;
}
