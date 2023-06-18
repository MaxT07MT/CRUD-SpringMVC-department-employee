package com.demo.mvc.controller;

import com.demo.mvc.exception.EmployeeException;
import com.demo.mvc.models.Employee;
import com.demo.mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/departments/{depId}/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/showEmp")
    public String allEmpByDepId(@PathVariable("depId") int depId, Model model) {
        model.addAttribute("employee", employeeService.findByDepId(depId));
        return "employees/employee";
    }


    @GetMapping("/{id}/empShowById")
    public String empById(@PathVariable("depId") int depId,
                          @PathVariable("id") int id,
                          Model model){
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/empShowById";
    }


    @GetMapping("/newEmp")
    public String newEmployee(@ModelAttribute("employee") Employee employee) {

        return "employees/empAdd";
    }

    @PostMapping("/createEmp")
    public String createEmp(@PathVariable("depId") int depId,
                            @ModelAttribute("employee")
                            @Valid Employee employee, Model model,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employees/empAdd";
        try {
            employeeService.create(employee);
            return "redirect:/departments/{depId}/employees/showEmp";
        }catch (EmployeeException error){
            model.addAttribute("employee", employee);
            model.addAttribute("error", error.violations);
            model.addAttribute("errorMsg", error.getMessage());
            return "employees/empAdd";
        }
    }
    @GetMapping("/{id}/editEmp")
    public String editEmp(
            @PathVariable("depId") int depId,
            @PathVariable("id") int id,
            Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/empEdit";
    }

    @PostMapping("/{id}/edit")
    public String updateEmp(@ModelAttribute("employee")
                                @Valid Employee employee, BindingResult bindingResult,
                            @PathVariable("depId") int depId,
                            @PathVariable("id") int id,
                            Model model) {
        if (bindingResult.hasErrors())
            return "employees/empEdit";
        try {
            employeeService.update(employee);
            return "redirect:/departments/{depId}/employees/{id}/empShowById";
        }catch (EmployeeException error){
            model.addAttribute("employee", employee);
            model.addAttribute("error", error.violations);
            model.addAttribute("errorMsg", error.getMessage());
            return "employees/empEdit";
        }
    }

    @GetMapping("/{id}/delEmp")
    public String delete(Model model,
                         @PathVariable("id") int id, @PathVariable int depId){
                         model.addAttribute("employee",
                         employeeService.findById(id));
        return "employees/empDelete";
    }

    @PostMapping("/{id}/delete")
    public String deleteEmp(Model model,
                            @ModelAttribute("employee")
                            @Valid Employee employee,
                            @PathVariable("id") int id) {
        employeeService.delete(employee);
        return "redirect:/departments/{depId}/employees/showEmp";
    }
}
