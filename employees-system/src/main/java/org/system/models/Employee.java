package org.system.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
public class Employee extends BaseEntity {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;


    /**
     * the employee id
     */
    @NotBlank(message = "Employee ID is mandatory")
    @UniqueElements(message = "Employee ID is unique ")
    @Column(name = "employee_id", unique = true, nullable = false)
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
     * the employee hire date
     */
    @NotBlank(message = "Employee hire date is mandatory")
    @Column(nullable = false, name = "hire_date")
    private LocalDate hireDate;
    /**
     * the employee employment status
     */
    @NotBlank(message = "Employee employment status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "employment_status")
    private EmploymentStatus employmentStatus;
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
     * the employee contact information.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_contact_info")
    private ContactInformation contactInformation;
}
