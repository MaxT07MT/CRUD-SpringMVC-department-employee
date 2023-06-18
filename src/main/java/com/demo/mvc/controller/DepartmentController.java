package com.demo.mvc.controller;

import com.demo.mvc.exception.DepartmentException;
import com.demo.mvc.models.Department;
import com.demo.mvc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }



    @GetMapping()
    public String allDep(Model model) {
        model.addAttribute("department", departmentService.getAll());
        return "departments/department";
    }


@GetMapping("/{id}")
public String showByIdDep(@PathVariable("id") int id, Model model) {
    model.addAttribute("department", departmentService.findById(id));
    return "departments/depShowById";
}

    @GetMapping("/newDep")
    public String newDepartment(@ModelAttribute("department") Department department) {
        return "departments/depAdd";
    }


    @PostMapping("depAdd")
    public String create(@ModelAttribute("department") @Valid Department department, Model model,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "departments/depAdd";
            try {
                departmentService.create(department);
                return "redirect:/departments";

            }catch (DepartmentException error){
                model.addAttribute("department", department);
                model.addAttribute("error", error.violations);
                model.addAttribute("errorMsg", error.getMessage());
                return "departments/depAdd";
            }

    }


    @GetMapping("/depEdit/{id}")
    public String editDep(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", departmentService.findById(id));
        return "departments/depEdit";
    }

    @PostMapping("/edit/{id}")
    public String updateDep(Model model,
                            @ModelAttribute("department")
                            @Valid Department department,
                            BindingResult bindingResult,
                            @PathVariable("id") int id ) {
        if (bindingResult.hasErrors())
            return "departments/depEdit";
        try {
            departmentService.update(department);
            return "redirect:/departments/{id}";
        }catch (DepartmentException error){
            model.addAttribute("department", department);
            model.addAttribute("error", error.violations);
            model.addAttribute("errorMsg", error.getMessage());
            return "departments/depEdit";
        }

    }

    @GetMapping("/{id}/deleteDep")
    public String doDeleteDep(Model model, @ModelAttribute("department")
                              @Valid Department department,
                              @PathVariable("id") int id) {
        model.addAttribute("department", departmentService.findById(id));
        return "departments/depDelete";
    }
    @PostMapping("/{id}/delete")
    public String deleteDep(Model model, @ModelAttribute("department")
    @Valid Department department,
    @PathVariable("id") int id) {
        departmentService.delete(department);
        return "redirect:/departments";
    }
}

