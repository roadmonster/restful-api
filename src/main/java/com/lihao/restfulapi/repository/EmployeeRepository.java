package com.lihao.restfulapi.repository;

import com.lihao.restfulapi.entities.Department;
import com.lihao.restfulapi.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository implements ObjectRepository<Employee>{

    private Map<Integer, Employee> repository;

    @Autowired
    private DepartmentRepository deptRepo;

    private static Integer initId = 1001;

    public EmployeeRepository( ) throws ParseException {
        this.repository = new HashMap<>();
        this.repository.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1,
                new Department(101, "D-AA"),
                new SimpleDateFormat("yyyy/MM/dd").parse("1989/2/11")));
        this.repository.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1,
                new Department(102, "D-BB"),
        new SimpleDateFormat("yyyy/MM/dd").parse("1989/2/12")));
        this.repository.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0,
                new Department(103, "D-CC"),
                new SimpleDateFormat("yyyy/MM/dd").parse("1989/2/13")));
        this.repository.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0,
                new Department(104, "D-DD"),
                new SimpleDateFormat("yyyy/MM/dd").parse("1999/2/11")));
        this.repository.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1,
                new Department(105, "D-EE"),
                new SimpleDateFormat("yyyy/MM/dd").parse("1969/3/11")));
    }

    @Override
    public void store(Employee employee) {
        if(employee.getId() == null){
            employee.setId(initId++);
        }

        employee.setDepartment(deptRepo.retrieve(employee.getDepartment().getId()));

        repository.put(employee.getId(), employee);
    }

    @Override
    public Employee retrieve(int id) {
        return repository.get(id);
    }

    @Override
    public Employee search(String name) {
        Collection<Employee> emps = repository.values();
        for(Employee emp: emps){
            if(emp.getLastName().equalsIgnoreCase(name)){
                return emp;
            }
        }
        return null;
    }

    @Override
    public Employee delete(int id) {
        Employee e = repository.get(id);
        repository.remove(id);
        return e;
    }

    public Collection<Employee> getAll(){
        return repository.values();
    }
}
