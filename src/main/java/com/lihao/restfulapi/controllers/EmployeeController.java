package com.lihao.restfulapi.controllers;
import com.lihao.restfulapi.entities.Department;
import com.lihao.restfulapi.entities.Employee;
import com.lihao.restfulapi.repository.DepartmentRepository;
import com.lihao.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Collection;

//need to use controller decorator in order to make themyleaf to render html
@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository empRepo;

    @Autowired
    DepartmentRepository deptRepo;

    /**
     * Get all the employee object from the repo
     * set the model attribute of emps with the result from the repo
     * let theymeleaf know the html to find and render
     * @param model
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> emps = empRepo.getAll();

        model.addAttribute("emps", emps);

        return "emp/list";
    }
    /**
     * Get all the departments from the repo and send the result to add.html
     * return to the add.html
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = deptRepo.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    /**
     * Delegating to the employee repository to save the employee
     * redirects to the employee list page after successfully insert it.
     * @param emp
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee emp){
        System.out.println("Saved employee info: " + emp);
        this.empRepo.store(emp);
        return "redirect:/emps";
    }

    /**
     * retrieve the employee detail from the repo with the given id
     * retrieve all the departments for choosing from the repo
     * send both results to the model to be available for html to render
     * return the add.html page
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee emp = this.empRepo.retrieve(id);
        model.addAttribute("emp", emp);

        Collection<Department> departments = this.deptRepo.getDepartments();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    /**
     * store the given employee object(sent from the frontend html)
     * redirect back to the employee list
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("Employee info updated: " + employee);
        this.empRepo.store(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        this.empRepo.delete(id);
        return "redirect:/emps";
    }


}
