package com.lihao.restfulapi.repository;

import com.lihao.restfulapi.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentRepository implements ObjectRepository<Department>{

    private final Map<Integer, Department> repository;

    public DepartmentRepository( ) {
        this.repository = new HashMap<>();
    }

    @Override
    public void store(Department department) {
        this.repository.put(department.getId(), department);
    }

    @Override
    public Department retrieve(int id) {
        return this.repository.get(id);
    }

    @Override
    public Department search(String name) {
        Collection<Department> depts = this.repository.values();
        for(Department dep: depts){
            if(dep.getDepartmentName().equalsIgnoreCase(name)){
                return dep;
            }
        }
        return null;
    }

    @Override
    public Department delete(int id) {
        Department dept = this.repository.get(id);
        this.repository.remove(id);
        return dept;
    }

    public Collection<Department>getDepartments(){
        return this.repository.values();
    }
}
