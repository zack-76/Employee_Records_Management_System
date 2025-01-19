package org.system.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.system.dto.EmployeeDTO;
import org.system.mapper.EmployeeMapper;
import org.system.models.ContactInformation;
import org.system.models.Employee;
import org.system.models.EmploymentStatus;
import org.system.models.MaritalStatus;
import org.system.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository repository;


    @PostConstruct
    public void init() {
        List<Employee> employees = new ArrayList<>();
        for (long i = 1; i <= 500; i++) {
            ContactInformation contactInformation = new ContactInformation("emp" + i + "@mail.com", "+123 676888" + i , MaritalStatus.valueOf("SINGLE"));
            employees.add(new Employee("emp" + i, "tata" + i, "Emp", LocalDate.now().plusDays(i), EmploymentStatus.valueOf("ACTIVE"), "street" + i, "department" + 1, contactInformation));
        }
        this.repository.saveAll(employees);
    }


    public List<EmployeeDTO> getAllEmployees() {
        log.info("getAllEmployees");
        return EmployeeMapper.mapList(repository.findAll(), EmployeeDTO.class);
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        return EmployeeMapper.mapToDto(repository.findById(id), EmployeeDTO.class);
    }

    public String createEmployee(EmployeeDTO employee) {
        repository.save(EmployeeMapper.mapToEntity(employee, Employee.class));
        return "Employee created";
    }

    public String updateEmployee(Integer id, EmployeeDTO employee) {
        repository.save(EmployeeMapper.mapToEntity(employee, Employee.class));
        return "Employee updated";
    }

    public void deleteEmployee(Integer id) {
        repository.deleteById(id);
    }
}
