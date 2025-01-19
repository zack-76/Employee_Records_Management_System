package org.system.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.system.dto.EmployeeDTO;
import org.system.mapper.EmployeeMapper;
import org.system.models.Employee;
import org.system.repository.EmployeeRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@NoArgsConstructor
@Slf4j
public class EmployeeService {
    private EmployeeRepository repository;


    public List<EmployeeDTO> getAllEmployees() {
        return EmployeeMapper.mapList(repository.findAll(), EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployeesByDepartment(String department) {
        return EmployeeMapper.mapList(repository.findEmployeeByDepartment(department), EmployeeDTO.class);
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
