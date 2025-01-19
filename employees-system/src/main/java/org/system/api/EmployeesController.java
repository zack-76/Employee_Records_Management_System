package org.system.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.system.dto.EmployeeDTO;
import org.system.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/emp")
@Tag(name = "Employee management", description = "Operations related to managing employees")
@NoArgsConstructor
public class EmployeesController {

    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees", description = "Fetch all employees from the system")
    @ApiResponse(responseCode = "200", description = "List of employees retrieved")
    @GetMapping
    @PreAuthorize("hasAnyRole('HR', 'MANAGER', 'ADMIN')")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }


    @Operation(summary = "Get all employees by department", description = "Fetch all employees from the system")
    @ApiResponse(responseCode = "200", description = "List of employees retrieved")
    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('HR', 'MANAGER', 'ADMIN')")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesByDepartment(@Parameter(in = ParameterIn.PATH, description = "employee id", required=true, schema=@Schema()) @PathVariable String department) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDepartment(department));
    }

    @Operation(summary = "Create an employee", description = "Create a new employee in the system")
    @ApiResponse(responseCode = "201", description = "employee created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    @PostMapping
    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    public ResponseEntity<String> createUser(
            @RequestBody(description = "Employee details", required = true, content = @Content(schema = @Schema(implementation = EmployeeDTO.class)))
            @org.springframework.web.bind.annotation.RequestBody EmployeeDTO employeeDTO) {

        return ResponseEntity.status(201).body(employeeService.createEmployee(employeeDTO));
    }

    @Operation(summary = "update an employee", description = "update an employee in the system")
    @ApiResponse(responseCode = "200", description = "employee updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HR') or (hasRole('MANAGER') and @employeePermissionEvaluator.hasAccess(authentication))")
    public ResponseEntity<String>  updateEmployee(@Parameter(in = ParameterIn.PATH, description = "employee id", required=true, schema=@Schema()) @PathVariable Integer id, @RequestBody(description = "Employee details", required = true, content = @Content(schema = @Schema(implementation = EmployeeDTO.class))) @org.springframework.web.bind.annotation.RequestBody EmployeeDTO employeeDto) {
        return ResponseEntity.status(200).body(employeeService.updateEmployee(id, employeeDto));
    }

    @Operation(summary = "Delete an employee", description = "delete an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete employee successful"),

            @ApiResponse(responseCode = "404", description = "employee not found", content = @Content)})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    public void deleteEmployee(@Parameter(in = ParameterIn.PATH, description = "employee id", required=true, schema=@Schema()) @PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}


@Component
class EmployeePermissionEvaluator {
    public boolean hasAccess(Authentication authentication) {
        String userRole = authentication.getAuthorities().iterator().next().getAuthority();
        return userRole.equals("MANAGER");
    }
}