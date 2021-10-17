package com.nerisadaily.testNerisa.tutorial.service;

import com.nerisadaily.testNerisa.tutorial.entity.Department;
import com.nerisadaily.testNerisa.tutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {


    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartment(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

    public Department getDepartmentByName(String departmentName);
}
