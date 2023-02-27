package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentApi {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;
    @GetMapping()
    public String getAllDepartments(Model model){
        model.addAttribute("departments" ,departmentService.getAllDepartments());
        return "departmentsPage";
    }
    @GetMapping("{hospitalId}/new4")
    public String create(Model model,@PathVariable("hospitalId") Long hospitalId){
        model.addAttribute("department",new Department());
        model.addAttribute("hospitals", hospitalService.getHospitalById(hospitalId));
        return "newDepartment";
    }
    @PostMapping("{hospitalId}/save4")
    public String save(@ModelAttribute("newDepartment") Department department,
                       @PathVariable("hospitalId") Long id){
        departmentService.saveDepartment(department,id);
        return "redirect:/hospitals/{hospitalId}/departments";
    }
    @DeleteMapping("{departmentId}/delete")
    public String delete(@PathVariable("departmentId")Long id){
        departmentService.deleteDepartment(id);

        return "redirect:/hospitals";
    }
    @GetMapping("{departmentId}/editDepartment")
    public String edit(Model model,@PathVariable("departmentId")Long departmentId){
        model.addAttribute("department",departmentService.getDepartmentById(departmentId));
        return "editDepartment";
    }
    @PutMapping("{departmentId}/update")
    public String update(@ModelAttribute("department") Department department,
                         @PathVariable("departmentId") Long departmentId){
       departmentService.updateDepartment(departmentId, department);
       return "redirect:/hospitals";
    }
}
