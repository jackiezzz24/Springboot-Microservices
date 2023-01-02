package net.javaguides.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.exception.ResourceNotFoundException;
import net.javaguides.employeeservice.mapper.AutoEmployeeMapper;
import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        // Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        Employee saveEmployee = employeeRepository.save(employee);

        // EmployeeDto saveEmployeeDto = EmployeeMapper.mapToEmployeeDto(saveEmployee);
        EmployeeDto saveEmployeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(saveEmployee);

        return saveEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

        // EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }
}
