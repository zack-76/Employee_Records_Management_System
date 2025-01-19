package org.system.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Entity
@Table(name = "employees")
public class Employee {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * the employee id
     */
    @NotBlank(message = "Employee ID is mandatory")
    @Id
    private String employeeId;
    /**
     * The employee full name
     */
    @NotBlank(message = "Employee full name is mandatory")
    @Column(nullable = false, name = "full_name")
    private String fullName;
    /**
     * the employee job title
     */
    @NotBlank(message = "Employee job title is mandatory")
    @Column(nullable = false, name = "job_title")
    private String jobTitle;
    /**
     * the employee employment status
     */
    @NotBlank(message = "Employee employment status is mandatory")
    @Column(nullable = false, name = "employment_status")
    private String employmentStatus;
    /**
     * the employee address
     */
    @NotBlank(message = "Employee address is mandatory")
    @Column(nullable = false, name = "address")
    private String address;
    /**
     * the employee department
     */
    @NotBlank(message = "Employee department is mandatory")
    @Column(nullable = false, name = "department")
    private String department;

    /**
     * the employee contact information email.
     */
    @Email(message = "Email should be valid")
    @Column(nullable = false, name = "contact_information")
    private String contactInformation;
}
