package com.lihao.restfulapi.repository;

import com.lihao.restfulapi.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository implements ObjectRepository<Employee>{

    private Map<Integer, Employee> repository;

    public EmployeeRepository( ) {
        this.repository = new HashMap<>();
    }

    @Override
    public void store(Employee employee) {
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
