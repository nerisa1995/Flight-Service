package com.nerisadaily.testNerisa.tutorial.service;

import com.nerisadaily.testNerisa.tutorial.entity.Department;
import com.nerisadaily.testNerisa.tutorial.error.DepartmentNotFoundException;
import com.nerisadaily.testNerisa.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(@Valid Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartment(Long departmentId) throws DepartmentNotFoundException {
//        Optional<Department> result = departmentRepository.findById(departmentId);
//        Department theDepartment = null;
//        if(result.isPresent()){
//            theDepartment = result.get();
//        }
//        return theDepartment;
//    }

        Optional<Department> department =
                departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Avaiable");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        //First we get the department which is already avaiable in the database
        Department depDB = departmentRepository.findById(departmentId).get();
        //After we get the particular object, here we check if there are any values. If there are any values then we update
        if(Objects.nonNull(department.getDepartmentName())&&
                !"".equalsIgnoreCase(department.getDepartmentName())){
            depDB.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentAddress())&&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode())&&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentCode(department.getDepartmentCode());
        }

        return departmentRepository.save(depDB);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
         return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);

    }
}
