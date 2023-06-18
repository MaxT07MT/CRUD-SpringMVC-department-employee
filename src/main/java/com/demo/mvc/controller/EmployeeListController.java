package com.demo.mvc.controller;

import com.demo.mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeListController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeListController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public String employeeList(Model model){
        model.addAttribute("employee", employeeService.findAll());
        return "employees/empList";
    }
    @GetMapping("/{id}")
    public String showByIdEmp(
            @PathVariable("id") int id,
            Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/empShowById";
    }
}
