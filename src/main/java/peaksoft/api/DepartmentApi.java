package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.service.DepartmentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentApi {
    private final DepartmentService departmentService;
    @GetMapping()
    public String getAllDepartments(Model model){
        model.addAttribute("departments" ,departmentService.getAllDepartments());
        return "departmentsPage";
    }
    @GetMapping("/new4")
    public String create(Model model){
        model.addAttribute("newDepartment",new Department());
        return "newDepartment";
    }
    @PostMapping("/save4")
    public String save(@ModelAttribute("newDepartment") Department department){
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }
    @DeleteMapping("{departmentId}/delete")
    public String delete(@PathVariable("departmentId")Long id){
        departmentService.deleteDepartment(id);
        departmentService.deleteDepartment2(id);
        return "redirect:/departments";
    }
    @GetMapping("{departmentId}/editDepartment")
    public String edit(Model model,@PathVariable("departmentId")Long id){
        model.addAttribute("department",departmentService.getDepartmentById(id));
        return "editDepartment";
    }
    @PutMapping("{departmentId}/update")
    public String update(@PathVariable("departmentId") Long id,
                         @ModelAttribute("department") Department department){
       departmentService.updateDepartment(id, department);
       return "redirect:/departments";
    }
}
